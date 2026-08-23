package org.firstinspires.ftc.teamcode.sousutils.startupMenu;

import java.util.Arrays;

public class StartupFormat {

    private StartupFormat() { /* shouldn't be constructed unless we add settings(?) */ }

    public static String repeatChar(char toRepeat, int repeatTimes)
    {
        if (repeatTimes <= 0) { return ""; }

        char[] builder = new char[repeatTimes];
        Arrays.fill(builder, toRepeat);

        return new String(builder);
    }

    public static String justifyBetween(int space, String... content)
    {
        return justifyBetween(space, ' ', content);
    }

    public static String justifyBetween(int space, char justifyChar, String... content)
    {

        int actualSpace = space;
        for (String data : content) { actualSpace -= data.length(); }

        if (actualSpace < 0) { /* error condition, add throw later */ return ""; }

        int gaps = content.length - 1;

        StringBuilder justified = new StringBuilder();

        if (gaps == 0) {
            justified.append(content[0]);
            justified.append(repeatChar(justifyChar, actualSpace));
        } else {
            int spacesPerGap = actualSpace / gaps;
            int leftover = actualSpace % gaps;

            for (int i = 0; i < gaps; i++)
            {
                justified.append(content[i]);
                justified.append(repeatChar(justifyChar, spacesPerGap));

                if (leftover > 0)
                {
                    justified.append(justifyChar);
                    leftover -= 1;
                }
            }

            justified.append(content[content.length - 1]); // i remembered then forgot :(
        }

        return justified.toString();
    }

    public static String center(int space, String message)
    {
        if (message == null) { /* check other calls for similar error */ return ""; }
        if (message.length() > space) { /* error cond again */ return ""; }

        //if (space < 0) { /* more error conds */ return ""; }
        // already is technically covered by second if but wondering if it's better to keep it
        // to be clear

        int actualSpace = space - message.length();
        int paddingPerSide = actualSpace / 2;
        boolean hasLeftover = actualSpace % 2 != 0;

        char centerChar = ' ';
        StringBuilder centered = new StringBuilder(space);

        centered.append(repeatChar(centerChar, paddingPerSide));
        if (hasLeftover) { centered.append(centerChar); }

        centered.append(message);
        centered.append(repeatChar(centerChar, paddingPerSide));

        return centered.toString();

    }

    public static String surround(String message, String left, String right)
    {
        return left + message + right;
    }

    public static String truncate(String message, String indicator, int maxLength)
    {
        if (indicator.length() > maxLength) {/* error condition here */ return ""; }

        String truncated = message;

        if (truncated.length() > maxLength) {
            truncated  = message.substring(maxLength - indicator.length()) + indicator;
        }

        return truncated;
    }

}
