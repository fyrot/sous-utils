package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.robot.constants.hardware.DriveHardware;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(7.8)
            .forwardZeroPowerAcceleration(-35.39404413214112)
            .lateralZeroPowerAcceleration(-57.19300602054316)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.08, 0, 0.007, 0.01))
            .headingPIDFCoefficients(new PIDFCoefficients(1.1, 0, 0.001,0.021)) // prev 0.82
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.008, 0, 0.001, 0.6, 0.010))
            .useSecondaryDrivePIDF(false)
            .useSecondaryHeadingPIDF(false)
            .useSecondaryTranslationalPIDF(false);
    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(0.8)
            .rightFrontMotorName(DriveHardware.FRONT_RIGHT)
            .rightRearMotorName(DriveHardware.REAR_RIGHT)
            .leftFrontMotorName(DriveHardware.FRONT_LEFT)
            .leftRearMotorName(DriveHardware.REAR_LEFT)
            .rightFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD);


    public static PinpointConstants pinpointLocalizerConstants = new PinpointConstants()
            .forwardPodY((14.375 / 2) - 6.0)
            .strafePodX(1.25 - (16.75 / 2))
            .hardwareMapName("pinpoint")
            .distanceUnit(DistanceUnit.INCH)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);


    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(pinpointLocalizerConstants)
                .build();
    }


}