package org.firstinspires.ftc.teamcode.sousutils.soundLibrary;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class SoundLine {
    private String soundLocation;
    private int soundCode = 0;
    private int probability;

    public SoundLine(String soundLocation, int probability, HardwareMap hardwareMap)
    {
        this.soundLocation = soundLocation;
        this.probability = Math.max(Math.abs(probability), 1);

        findSound(hardwareMap);
    }

    public SoundLine(String soundLocation, HardwareMap hardwareMap)
    {
        this(soundLocation, 1, hardwareMap);
    }

    private void findSound(HardwareMap hardwareMap)
    {
        soundCode = hardwareMap.appContext.getResources().getIdentifier(
            soundLocation, "raw", hardwareMap.appContext.getPackageName()
        );
    }


    protected int getSoundCode()
    {
        return soundCode;
    }

    protected int getProbability()
    {
        return probability;
    }

    protected String getSoundLocation()
    {
        return soundLocation;
    }

}
