package org.firstinspires.ftc.teamcode.sousutils.startupMenu;

import java.util.ArrayList;
import java.util.List;

class StartupMenuTester {
    public static void main(String[] args) {
        StartupMenu menu = new StartupMenu(null, null).setTitle("\uD83E\uDD16 Example Menu");
        List<StartupMenuLine> exampleLines = new ArrayList<StartupMenuLine>();

        exampleLines.add(
                new StartupMenuLine.Builder("key1", StartupLineType.STRING)
                        .setDisplayLabel("Alliance")
                        .addStringOptions(new String[]{"Red", "Blue", "None"})
                        .build()
        );

        exampleLines.add(
                new StartupMenuLine.Builder("key2", StartupLineType.DOUBLE)
                        .setDisplayLabel("OPR")
                        .setRange(-10.0, 300.0)
                        .build()
        );

        exampleLines.add(
                new StartupMenuLine.Builder("key3", StartupLineType.BOOLEAN)
                        .setDisplayLabel("Passed Inspection?")
                        .setDefaultBoolean(true)
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
