package org.firstinspires.ftc.teamcode.opModes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TempTelemetryRuler extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        int extendUntil = 50;

        /*
        // test width
        StringBuilder rowStringBuilder = new StringBuilder();
        for (int i = 0; i <= extendUntil; i++) {
            rowStringBuilder.append(i % 10);
        }
        telemetry.addLine(rowStringBuilder.toString());
        */

        /*
        // test height
        for (int i = 0; i <= extendUntil; i++) {
            telemetry.addLine(i % 10);
        }
         */

        telemetry.update();


    }
}
