package org.firstinspires.ftc.teamcode.misc;

// putting random stuff in here instead of .gitkeep
public class TempUtils {
    public static double clamp(double val, double min, double max)
    {
        return Math.min(Math.max(min, val), max);
    }

    public static int clamp(int val, int min, int max)
    {
        return Math.min(Math.max(min, val), max);
    }
}
