package org.firstinspires.ftc.teamcode.robot.controls;

import org.firstinspires.ftc.teamcode.robot.bridge.RobotCoordinator;

public interface Controller {
    void init(RobotCoordinator addedTo);
    void ping();

}
