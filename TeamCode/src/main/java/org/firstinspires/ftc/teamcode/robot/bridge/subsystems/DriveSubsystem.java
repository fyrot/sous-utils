package org.firstinspires.ftc.teamcode.robot.bridge.subsystems;

import static com.pedropathing.ivy.commands.Commands.instant;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.pedro.PedroCommands;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.misc.Alliance;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.robot.bridge.SubsystemManager;

public class DriveSubsystem implements SubsystemManager {

    private Follower follower;
    private Alliance alliance = Alliance.NONE;



    //
    private double forward = 0;
    private double strafe = 0;
    private double turnX = 0;
    private double turnY = 0;

    public DriveSubsystem(HardwareMap hardwareMap)
    {
        this.follower = Constants.createFollower(hardwareMap);
        follower.setPose(new Pose(0, 0));


    }

    public Follower getFollower()
    {
        return follower;
    }

    @Override
    public void init() {
        follower.startTeleOpDrive();
    }

    @Override
    public void periodic() {
        if (follower.isTeleopDrive())
        {
            follower.setTeleOpDrive(forward, strafe, turnX);
        }
        follower.update();
    }

    public void setPose(Pose newPose)
    {
        follower.setPose(newPose);
    }

    public Pose getPose()
    {
        return follower.getPose();
    }

    public void cacheDriveInputs(double forward, double strafe, double turnX, double turnY)
    {
        this.forward = forward;
        this.strafe = strafe;
        this.turnX = turnX;
        this.turnY = turnY;
    }

    public Command drivePath(PathChain path)
    {
        return PedroCommands.follow(follower, path)
                .requiring(this);
    }

    public Command holdCurrent()
    {
        return PedroCommands.hold(follower)
                .requiring(this);
    }

    public Command turnTo(double radians)
    {
        return PedroCommands.turnTo(follower, radians)
                .requiring(this);
    }

    public Command resumeTeleopDrive() {
        return instant(() -> follower.startTeleOpDrive()).requiring(this);
    }
}
