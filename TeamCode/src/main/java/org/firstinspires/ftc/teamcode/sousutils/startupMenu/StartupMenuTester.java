package org.firstinspires.ftc.teamcode.sousutils.startupMenu;

import java.util.ArrayList;
import java.util.List;

public class StartupMenuTester {
    public static void main(String[] args) {
        StartupMenu menu = new StartupMenu(null, null);
        List<StartupMenuLine> exampleLines = new ArrayList<StartupMenuLine>();

        exampleLines.add(
                new StartupMenuLine.Builder("key1", StartupLineType.STRING)
                        .setDisplayLabel("Fav. Color")
                        .addStringOptions(new String[]{"Red", "Orange", "Yellow", "Azure"})
                        .build()
        );

        exampleLines.add(
                new StartupMenuLine.Builder("key2", StartupLineType.DOUBLE)
                        .setDisplayLabel("GPA")
                        .setRange(0.0, 5.0)
                        .build()
        );

        exampleLines.add(
                new StartupMenuLine.Builder("key3", StartupLineType.BOOLEAN)
                        .setDisplayLabel("Very Cool?")
                        .setDefaultBoolean(false)
                        .build()
        );

        System.out.println(StartupFormat.justifyBetween(30, ' ', "Hi", "How are you?"));

        menu.addLines(exampleLines.toArray(new StartupMenuLine[0]));
        menu.initWindow();

        for (String line : menu.getRendered()) {
            System.out.println(line);
        }
    }
}
