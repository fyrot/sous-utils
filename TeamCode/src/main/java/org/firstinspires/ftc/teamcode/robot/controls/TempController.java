package org.firstinspires.ftc.teamcode.robot.controls;

import com.pedropathing.ivy.Scheduler;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robot.bridge.RobotCoordinator;
import org.firstinspires.ftc.teamcode.robot.bridge.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.robot.bridge.subsystems.IntakeSubsystem;

public class TempController implements Controller {
    private Gamepad gamepad;
    private RobotCoordinator robotRef;

    private DriveSubsystem driveRef;
    private IntakeSubsystem intakeRef;
    public TempController(Gamepad gamepad)
    {
        this.gamepad = gamepad;
    }

    @Override
    public void init(RobotCoordinator addedTo)
    {
        this.robotRef = addedTo;
        this.driveRef = robotRef.getDrive();
        this.intakeRef = robotRef.getIntake();
    }

    @Override
    public void ping() {

        driveRef.cacheDriveInputs(
                -gamepad.left_stick_y,
                -gamepad.left_stick_x,
                -gamepad.right_stick_x,
                -gamepad.right_stick_y
        );

        if (gamepad.a)
        {
            Scheduler.schedule(intakeRef.spinForward());
            // :O
        }

        if (gamepad.b)
        {
            Scheduler.schedule(intakeRef.pauseIntake());
        }

        if (gamepad.y)
        {
            Scheduler.schedule(intakeRef.spinBackward());
        }
    }
}
