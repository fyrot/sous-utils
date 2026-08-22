package org.firstinspires.ftc.teamcode.robot.bridge.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.bridge.SubsystemManager;
import org.firstinspires.ftc.teamcode.robot.constants.hardware.IntakeHardware;

public class IntakeSubsystem implements SubsystemManager {
    private final DcMotorEx intakeMotor;
    private double intakePower = 0.0;

    public IntakeSubsystem(HardwareMap hardwareMap)
    {
        intakeMotor = hardwareMap.get(DcMotorEx.class, IntakeHardware.INTAKE_MOTOR);
    }

    @Override
    public void init()
    {

    }

    @Override
    public void periodic()
    {
        intakeMotor.setPower(intakePower);
    }

    public Command spinForward()
    {
        return instant(() -> intakePower = 0.9).requiring(this);
    }

    public Command spinBackward()
    {
        return instant(() -> intakePower = -0.9).requiring(this);
    }

    public Command pauseIntake()
    {
        return instant(() -> intakePower = 0.0).requiring(this);
    }
}
