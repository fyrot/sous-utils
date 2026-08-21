package org.firstinspires.ftc.teamcode.robot.bridge;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotCoordinator implements SubsystemManager {

    private List<SubsystemManager> subsystems;
    private HardwareMap hardwareMap;
    public RobotCoordinator(HardwareMap hardwareMap) {
        // ...
        this.hardwareMap = hardwareMap;
        subsystems = new ArrayList<SubsystemManager>();
    }

    @Override
    public void init() {

    }

    @Override
    public void periodic() {

    }
}
