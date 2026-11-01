package org.firstinspires.ftc.teamcode.sousutils.stratagemInput;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.HashMap;
import java.util.Map;

public class StratagemInputs {


    private Gamepad gamepad;
    private int timeoutMs;
    private ElapsedTime timer;

    private StratagemNode head = new StratagemNode();
    private StratagemNode observed = head;

    public StratagemInputs(Gamepad gamepad, int timeoutMs) {
        this.gamepad = gamepad;
        this.timeoutMs = timeoutMs;
        timer = new ElapsedTime();
    }
    // test file for subtree work, awaiting finding old source code

    public void tick()
    {
        int elapsed = (int) timer.milliseconds();

        if (elapsed > timeoutMs)
        {

        }

        boolean changed = gamepad.dpadUpWasPressed()
                || gamepad.dpadRightWasPressed()
                || gamepad.dpadDownWasPressed()
                || gamepad.dpadLeftWasPressed();

        if (changed)
        {

            timer.reset();
        }
    }

}

class StratagemNode {
    StratagemNode up = null;
    StratagemNode right = null;
    StratagemNode down = null;
    StratagemNode left = null;

    boolean ends = false;
    StratagemInput reference = null;

    public StratagemNode()
    {}

    public StratagemNode(StratagemInput reference)
    {
        this.reference = reference;
    }

}
