package org.firstinspires.ftc.teamcode.opModes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.sousutils.startupMenu.StartupLineType;
import org.firstinspires.ftc.teamcode.sousutils.startupMenu.StartupMenu;
import org.firstinspires.ftc.teamcode.sousutils.startupMenu.StartupMenuLine;

import java.util.ArrayList;
import java.util.List;

@TeleOp(name = "Startup Menu", group = "Demos")
public class TempStartupMenu extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        StartupMenu menu = new StartupMenu(telemetry, gamepad1).setTitle("Real Demo");
        List<StartupMenuLine> exampleSettings = new ArrayList<StartupMenuLine>();

        for (int i = 0; i < 24; i++) {
            if (i % 3 == 0) {
                exampleSettings.add(
                        new StartupMenuLine.Builder(String.format("key%d", i), StartupLineType.STRING)
                                .setDisplayLabel(String.format("Option %d", i + 1))
                                .addStringOptions(new String[]{"Red", "Blue", "None"})
                                .build()
                );
            }
            if (i % 3 == 1) {
                exampleSettings.add(
                        new StartupMenuLine.Builder(String.format("key%d", i), StartupLineType.DOUBLE)
                                .setDisplayLabel(String.format("Option %d", i + 1))
                                .setRange(-10.0, 300.0)
                                .build()
                );
            }

            if (i % 3 == 2) {
                exampleSettings.add(
                        new StartupMenuLine.Builder(String.format("key%d", i), StartupLineType.BOOLEAN)
                                .setDisplayLabel(String.format("Option %d", i + 1))
                                .setDefaultBoolean(true)
                                .build()
                );
            }
        }

        menu.addLines(exampleSettings.toArray(new StartupMenuLine[0]));

        menu.run();

        waitForStart();

        while (opModeIsActive()) { }
    }
}
