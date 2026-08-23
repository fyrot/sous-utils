package org.firstinspires.ftc.teamcode.opModes.teleop;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.robot.bridge.RobotCoordinator;
import org.firstinspires.ftc.teamcode.robot.controls.Controller;
import org.firstinspires.ftc.teamcode.robot.controls.TempController;

@TeleOp(name = "BRO", group = "What Is This")
public class TempTeleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RobotCoordinator robot = new RobotCoordinator(hardwareMap);
        Controller inputOne = new TempController(gamepad1);
        robot.init();

        robot.addController(inputOne);

        waitForStart();

        while (opModeIsActive())
        {
            robot.periodic();
            Scheduler.execute();
        }

        robot.disable();
    }
}
