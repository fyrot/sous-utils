package org.firstinspires.ftc.teamcode.robot.bridge;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.bridge.subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.robot.bridge.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.robot.controls.Controller;

import java.util.ArrayList;
import java.util.List;

public class RobotCoordinator {

    private static RobotCoordinator robot;

    private DriveSubsystem driveSubsystem;
    private IntakeSubsystem intakeSubsystem;

    private List<SubsystemManager> subsystems;
    private List<Controller> inputs;
    private HardwareMap hardwareMap;


    public RobotCoordinator(HardwareMap hardwareMap) {
        // ...
        this.hardwareMap = hardwareMap;

        subsystems = new ArrayList<SubsystemManager>();
        inputs = new ArrayList<Controller>();

        this.driveSubsystem = createSubsystem(new DriveSubsystem(hardwareMap));
        this.intakeSubsystem = createSubsystem(new IntakeSubsystem(hardwareMap));

        List<LynxModule> robotHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule robotHub : robotHubs)
        {
            robotHub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
        }

        robot = this;
    }

    public DriveSubsystem getDrive()
    {
        return this.driveSubsystem;
    }

    public IntakeSubsystem getIntake()
    {
        return this.intakeSubsystem;
    }

    public void init() {
        inputs.clear();

        for (SubsystemManager subsystem : subsystems)
        {
            subsystem.init();
        }

    }

    public void periodic() {
        for (Controller controller : inputs) { controller.ping(); }

        for (SubsystemManager subsystem : subsystems)
        {
            subsystem.periodic();
        }
    }

    public void disable() {

    }

    private <T extends SubsystemManager> T createSubsystem(T subsystem)
    {
        subsystems.add(subsystem);
        return subsystem;
    }

    public void addController(Controller controller) {
        controller.init(this);
        inputs.add(controller);
    }

    public static RobotCoordinator getBot() {
        return robot;
    }

}
