package org.firstinspires.ftc.teamcode.sousutils.startupMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StartupMenuLine {
    private final StartupLineType lineType;
    private final String key;
    private String displayName;

    // string-specific
    private final List<String> stringOptions;

    // double-specific
    private final double min;
    private final double max;

    //
    private int stringInd = 0;
    private double doubleVal;
    private boolean boolVal;

    public StartupMenuLine(Builder lineBuilder)
    {
        this.key = lineBuilder.key;
        this.lineType = lineBuilder.lineType;

        this.displayName = lineBuilder.displayName;

        this.stringOptions = lineBuilder.stringOptions;

        this.min = lineBuilder.min;
        this.max = lineBuilder.max;

        this.doubleVal = lineBuilder.min;
        this.boolVal = lineBuilder.boolDefault;
    }

    public Object getValue()
    {
        switch (lineType) {
            case STRING:
                return stringOptions.get(stringInd);
            case DOUBLE:
                return doubleVal;
            case BOOLEAN:
                return boolVal;
        }
        return null;
    }

    public String getStringVal()
    {
        return getValue().toString();
    }

    public double getDoubleVal()
    {
        return (double) getValue();
    }

    public boolean getBooleanVal()
    {
        return (boolean) boolVal;
    }

    public void increment(double step)
    {
        if (lineType == StartupLineType.STRING) {
            stringInd = (stringInd + 1) % stringOptions.size();
        } else if (lineType == StartupLineType.DOUBLE) {
            doubleVal = Math.min(max, doubleVal + step);
        } else if (lineType == StartupLineType.BOOLEAN) {
            boolVal = !boolVal;
        }
    }

    public void decrement(double step)
    {
        if (lineType == StartupLineType.STRING) {

        } else if (lineType == StartupLineType.DOUBLE) {
            doubleVal = Math.max(min, doubleVal -= step);
        } else if (lineType == StartupLineType.BOOLEAN) {
            boolVal = !boolVal;
        }
    }

    public static class Builder {
        private final String key;
        private final StartupLineType lineType;

        private String displayName;

        // string-specific
        private List<String> stringOptions = new ArrayList<String>();

        // double-specific
        private Double min;
        private Double max;

        // boolean-specific
        private Boolean boolDefault;

        public Builder(String key, StartupLineType lineType) {
            this.key = key;
            this.lineType = lineType;
        }

        public Builder addStringOptions(String[] options) {
            Collections.addAll(stringOptions, options);
            return this;
        }

        public Builder setRange(double min, double max) {
            this.min = min;
            this.max = max;
            return this;
        }

        public Builder setDefaultBoolean(boolean defaultVal) {
            this.boolDefault = defaultVal;
            return this;
        }

        public Builder setDisplayLabel(String displayName)
        {
            this.displayName = displayName;
            return this;
        }

        public StartupMenuLine build()
        {
            validateGeneral();
            if (lineType == StartupLineType.STRING) {
                if (!validateString()) {
                    throw new StartupMenuException("Invalid string setup");
                    // TODO: custom message later
                }
                return new StartupMenuLine(this);
            } else if (lineType == StartupLineType.DOUBLE) {
                if (!validateDouble()) {
                    throw new StartupMenuException("Invalid double setup");
                    // same as before
                }
                return new StartupMenuLine(this);
            } else if (lineType == StartupLineType.BOOLEAN) {
                if (!validateBoolean()) {
                    throw new StartupMenuException("Invalid boolean setup");
                    // once again
                }
                return new StartupMenuLine(this);
            }
            return null; // this shouldn't be hit
        }

        private void validateGeneral()
        {
            if (displayName == null) { displayName = key; }
        }

        private boolean validateString()
        {
            boolean isValid = true;
            if (stringOptions.isEmpty()) { isValid = false; }
            return isValid;
        }

        private boolean validateDouble()
        {
            boolean isValid = true;
            if (min == null || max == null) { isValid = false; }

            return isValid;
        }

        private boolean validateBoolean()
        {
            boolean isValid = true;
            if (boolDefault == null) { isValid = false; }
            return isValid;
        }
    }

}
