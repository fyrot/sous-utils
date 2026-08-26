package org.firstinspires.ftc.teamcode.opModes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.sousutils.soundLibrary.SoundLib;

@TeleOp(name = "Temp Sound Lib", group = "Demos")
public class TempSoundLib extends LinearOpMode {
    @Override
    public void runOpMode() {
        SoundLib soundLib = new SoundLib()
                .addSound("black_panther.mp3")
                .addSound("winter_soldier.mp3", 2);

        int soundsPlayed = 0;
        boolean prevA = false;

        telemetry.addLine("Initializing..");
        telemetry.update();
        waitForStart();



        while (opModeIsActive())
        {
            if (gamepad1.a && !prevA)
            {
                String randomSong = soundLib.playRandom();
                soundsPlayed += 1;

                telemetry.addLine(String.format("Randomly played (%d): %s", soundsPlayed, randomSong));
                telemetry.update();
            }

            prevA = gamepad1.a;
        }



    }
}
