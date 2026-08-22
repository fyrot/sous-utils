package org.firstinspires.ftc.teamcode.robot.bridge;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.controls.Controller;

import java.util.ArrayList;
import java.util.List;

public class RobotCoordinator {

    private static RobotCoordinator robot;

    private List<SubsystemManager> subsystems;
    private List<Controller> inputs;
    private HardwareMap hardwareMap;


    public RobotCoordinator(HardwareMap hardwareMap) {
        // ...
        this.hardwareMap = hardwareMap;

        subsystems = new ArrayList<SubsystemManager>();
        inputs = new ArrayList<Controller>();

        robot = this;
    }

    public void init() {
        inputs.clear();
    }

    public void periodic() {
        for (Controller controller : inputs)
        {
            controller.ping();
        }
    }

    public void disable() {

    }

    public void addController(Controller controller) {
        inputs.add(controller);
    }

    public static RobotCoordinator getBot() {
        return robot;
    }
}
