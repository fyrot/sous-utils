package org.firstinspires.ftc.teamcode.opModes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Telemetry Ruler", group = "What is This")
public class TempTelemetryRuler extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        int extendUntil = 50;


        // test width
        /*StringBuilder rowStringBuilder = new StringBuilder();
        for (int i = 0; i <= extendUntil; i++) {
            rowStringBuilder.append(i % 10);
        }
        String rowString = rowStringBuilder.toString();
        telemetry.addLine();

         */

        // test height

        waitForStart();

        while (opModeIsActive())
        {
            //telemetry.addLine(rowString);
            for (int i = 0; i <= extendUntil; i++) {
                telemetry.addLine(String.valueOf(i % 10));
            }
            telemetry.update();
        }
        telemetry.update();

        // width 38
        // height 19
    }
}
