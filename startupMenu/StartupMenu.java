package org.firstinspires.ftc.teamcode.sousutils.startupMenu;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;

public class StartupMenu {
    private Telemetry telemetry;

    private int width; private int height; // dimensions of telemetry window in mono characters


    // LinkedHashSet would unironically actually kinda work here if we were using iterators
    private List<StartupMenuLine> settingLines;
    private int selectedInd = 0;
    private Gamepad input;

    private String title = "Startup Menu";
    private static final int TRANSMISSION_INTERVAL_MS = 25; // so we don't obliterate the connection
    private static final long RENDERING_INTERVAL_MS = 10;
    private static final int MAX_LABEL_LENGTH = 14;
    private long lastRenderTimestamp;

    private StartupMenu(Telemetry telemetry, Gamepad input) {
        this.telemetry = telemetry;
        this.input = input;

        // measurements based on counted monospace output for a standard driver hub
        this.width = 38;
        this.height = 19;

        settingLines = new ArrayList<StartupMenuLine>();

        initTelemetry();
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

    public void run()
    {
        boolean running = true;
        while (running)
        {
            running = handleInput();

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastRenderTimestamp > RENDERING_INTERVAL_MS)
            {
                render();
            }
        }


    }

    public boolean handleInput()
    {
        if (input.startWasReleased()) { return false; }
        // clauses for exit

        return true;

    }

    public void render()
    {
        // adjust for line budgeting here, title, separators, bottom info

    }



}
