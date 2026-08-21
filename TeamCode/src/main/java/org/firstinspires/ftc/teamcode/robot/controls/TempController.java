package org.firstinspires.ftc.teamcode.robot.controls;

import com.qualcomm.robotcore.hardware.Gamepad;

public class TempController implements Controller {
    private Gamepad gamepad;

    public TempController(Gamepad gamepad)
    {
        this.gamepad = gamepad;
    }

    @Override
    public void ping() {
        if (gamepad.aWasPressed()) {
            // :O
        }
    }
}
