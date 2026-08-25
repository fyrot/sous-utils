package org.firstinspires.ftc.teamcode.sousutils.startupMenu;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.teamcode.sousutils.startupMenu.StartupFormat.*;

public class StartupMenu {
    private Telemetry telemetry;

    private int width; private int height; // dimensions of telemetry window in mono characters


    // LinkedHashSet would unironically actually kinda work here if we were using iterators
    private List<StartupMenuLine> settingLines;
    private int selectedInd = 0;
    private int windowStart = -1;
    private int windowEnd = -1; // non-inclusive
    private Gamepad input;

    private String title = "Startup Menu";
    private static final String TRUNCATE_WITH = "...";
    private static final int TRANSMISSION_INTERVAL_MS = 25; // so we don't obliterate the connection
    private static final long RENDERING_INTERVAL_MS = 10;
    private static final int MAX_LABEL_LENGTH = 14;
    private long lastRenderTimestamp;

    public StartupMenu(Telemetry telemetry, Gamepad input) {
        this.telemetry = telemetry;
        this.input = input;

        // measurements based on counted monospace output for a standard driver hub
        this.width = 38;
        this.height = 19;

        settingLines = new ArrayList<StartupMenuLine>();

        if (telemetry != null) { initTelemetry(); }
        //initTelemetry();
    }


    public Object getValue(String key)
    {
        StartupMenuLine correctLine = null;
        for (StartupMenuLine line : settingLines)
        {
            if (key.equals(line.getKey())) { correctLine = line;}
        }
        return correctLine != null ? correctLine.getValue() : null;
    }

    public <T> T getValue(Class<T> dataType, String key)
    {
        Object foundValue = getValue(key);
        if (dataType.isInstance(foundValue)) {
            return dataType.cast(foundValue);
        } else {
            // invalid types
        }
        // not found, but the isInstance may trigger for null values too so this is temp.
        return null;
    }

    private void initTelemetry()
    {
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        // wonder if teams would want this flipped back or not?
        telemetry.setMsTransmissionInterval(TRANSMISSION_INTERVAL_MS);

        // clear telemetry
        telemetry.update();
        telemetry.update();

    }

    // shouldn't be needed to be used?
    public StartupMenu setDimensions(int width, int height)
    {
        this.width = width;
        this.height = height;

        return this;
    }

    public StartupMenu setTitle(String title)
    {
        this.title = title;

        return this;
    }

    public boolean addLine(StartupMenuLine line)
    {
        boolean canAdd = true;
        for (StartupMenuLine existing : settingLines)
        {
            if (line.getKey().equals(existing.getKey())) { canAdd = false; }
        }

        if (canAdd) { settingLines.add(line); }

        return canAdd;
    }

    public void addLines(StartupMenuLine[] lines)
    {
        for (StartupMenuLine line : lines) { addLine(line); }
    }

    public void initWindow()
    {
        int lineBudget = height;
        lineBudget -= 4; // title, separator, separator, bottom info

        this.windowStart = 0;
        this.windowEnd = Math.min(lineBudget, settingLines.size());
        this.selectedInd = this.windowStart;
    }

    public void run()
    {

        initWindow();
        long lastInput = System.currentTimeMillis();
        boolean running = true;
        while (running)
        {
            long currentTime = System.currentTimeMillis();
            long delta = currentTime - lastRenderTimestamp;

            running = handleInput((double) (currentTime - lastInput) / 1000.0);

            if (delta > RENDERING_INTERVAL_MS)
            {
                renderToTelemetry(getRendered());
                telemetry.update();
            }

            lastInput = currentTime;

        }

        finishRun();

    }

    public boolean handleInput(double delta)
    {
        if (input == null) { /* temp for testing, swap to false later */ return true; }
        if (input.startWasReleased()) { return false; }
        // clauses for exit

        StartupMenuLine line = settingLines.get(selectedInd);
        StartupLineType lineType = line.getLineType();

        if (input.dpadUpWasPressed()) {
            navigateIndex(-1);
            return true;
        } else if (input.dpadDownWasPressed()) {
            navigateIndex(1);
            return true;
        }

        if (input.aWasPressed()) {
            if (lineType == StartupLineType.BOOLEAN) { line.increment(0); return true; }
            if (lineType == StartupLineType.STRING) { line.increment(0); return true; }
        }

        int notches = 32;
        if (input.dpadRightWasPressed()) {
            if (lineType == StartupLineType.BOOLEAN) { line.increment(0); return true; }
            if (lineType == StartupLineType.STRING) { line.increment(0); return true; }
            if (lineType == StartupLineType.DOUBLE) {
                // make this cleaner later
                double stepSize = (line.getDoubleMaxVal() - line.getDoubleMinVal()) / notches;

                line.increment(stepSize);
                return true;
            }
        }

        if (input.dpadLeftWasPressed()) {
            if (lineType == StartupLineType.BOOLEAN) { line.decrement(0); return true; }
            if (lineType == StartupLineType.STRING) { line.decrement(0); return true; }
            if (lineType == StartupLineType.DOUBLE) {
                // make this cleaner later

                double stepSize = (line.getDoubleMaxVal() - line.getDoubleMinVal()) / notches;

                line.decrement(stepSize);
                return true;
            }
        }

        double GAMEPAD_DEADZONE = 0.02;
        if (Math.abs(input.left_stick_x) > GAMEPAD_DEADZONE)
        {
            if (lineType == StartupLineType.DOUBLE)
            {
                double sensitivity = 0.5; // tuned for 2s to go from one end to the other
                double speed = input.left_stick_x;
                double rangeWidth = line.getDoubleMaxVal() - line.getDoubleMinVal();
                double stepSize = delta * speed * sensitivity * rangeWidth;

                //line.increment(0.000001);
                line.increment(stepSize);
            }
        }



        return true;

    }

    private int indexClamp(int val, int min, int max)
    {
        return Math.min(Math.max(min, val), max);
    }

    private void navigateIndex(int step)
    {
        selectedInd = indexClamp(selectedInd + step, 0, settingLines.size() - 1);
        if (selectedInd < windowStart) { windowStart = selectedInd; windowEnd--; }
        if (selectedInd >= windowEnd) { /* >= because not inclusive */ windowEnd = selectedInd + 1; windowStart++; }
    }

    public List<String> getRendered()
    {
        // adjust for line budgeting here, title, separators, bottom info
        int lineBudget = height;
        if (lineBudget <= 5) { /* way too small to be useful */ return null; }

        List<String> allLines = new ArrayList<String>();
        allLines.add(center(width, title));
        allLines.add(repeatChar('-', width));
        lineBudget -= 4; // account for end already

        for (int lineInd = windowStart; lineInd < windowEnd; lineInd++)
        {
            StartupMenuLine currentLine = settingLines.get(lineInd);
            allLines.add(renderLine(currentLine, lineInd));
            lineBudget -= 1;
        }
        while (lineBudget > 0)
        {
            allLines.add("");
            lineBudget -= 1;
        }

        // ending separator and data line
        allLines.add(repeatChar('-', width));
        allLines.add(justifyBetween(width, createHiddenLinesLabel(), "Continue w/ START"));

        return allLines;
    }

    private String renderLine(StartupMenuLine line, int lineInd)
    {
        boolean selected = lineInd == selectedInd;


        String label = (selected ? "> " : "") + line.getDisplayName();

        String value = (selected ? surround(renderLineData(line), "< " ," >") : renderLineData(line));

        int totalLength = label.length() + value.length();
        // TODO: take another look at how we handle this later
        if (value.length() + TRUNCATE_WITH.length() + 3 > width) { return "Too long to render";}

        if (totalLength > width)
        {
            label = truncate(label, TRUNCATE_WITH,  label.length() - Math.abs(totalLength - width));
        }

        return justifyBetween(width, label, value);
    }

    private String renderLineData(StartupMenuLine line)
    {
        if (line.getLineType() == StartupLineType.STRING) {
            return line.getStringVal();
        } else if (line.getLineType() == StartupLineType.DOUBLE) {
            double min = line.getDoubleMinVal();
            double max = line.getDoubleMaxVal();
            double val = line.getDoubleVal();

            double percentage = (val - min) * 100 / (max - min);
            int percentageInt = (int) percentage;

            String parantheses = String.format("%d%%", percentageInt);
            if (min == val) { parantheses = "Min"; }
            if (max == val) { parantheses = "Max"; }

            return String.format("%.3f (%s)", val, parantheses);
        } else if (line.getLineType() == StartupLineType.BOOLEAN) {
            return line.getBooleanVal() ? "True" : "False";
        }
        return "Not reachable";
    }

    private String createHiddenLinesLabel()
    {
        int linesAbove = windowStart;
        int linesBelow = settingLines.size() - windowEnd;
        boolean hasAbove = linesAbove > 0;
        boolean hasBelow = linesBelow > 0;

        StringBuilder linesHiddenLabel = new StringBuilder();

        if (hasAbove) { linesHiddenLabel.append(String.format("%d above", linesAbove)); }
        if (hasBelow)
        {
            if (linesHiddenLabel.length() > 0) { linesHiddenLabel.append(", "); }
            linesHiddenLabel.append(String.format("%d below", linesBelow));
        }


        return linesHiddenLabel.toString();

    }

    private void renderToTelemetry(List<String> lines)
    {
        for (String line : lines)
        {
            telemetry.addLine(line);
        }
        lastRenderTimestamp = System.currentTimeMillis();
    }

    private void finishRun()
    {
        //
        for (StartupMenuLine line : settingLines)
        {
            // call onFinish
        }
    }



}
