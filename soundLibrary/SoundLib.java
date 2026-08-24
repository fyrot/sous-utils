package org.firstinspires.ftc.teamcode.sousutils.soundLibrary;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class SoundLib {
    private SoundPlayer player;
    private List<SoundLine> sounds;
    private HardwareMap hardwareMap;

    private boolean created = false;
    private int totalWeight = -1;

    public SoundLib(HardwareMap hardwareMap)
    {
        this.player = SoundPlayer.getInstance();
        this.sounds = new ArrayList<SoundLine>();
        this.hardwareMap = hardwareMap;
    }

    public SoundLib addLine(SoundLine sound)
    {
        if (created) { return this; }
        sounds.add(sound);
        return this;
    }

    public SoundLib addLines(SoundLine... lines)
    {
        if (created) { return this; }
        for (SoundLine line : lines)
        {
            addLine(line);
        }
        return this;
    }

    public SoundLib create()
    {
        calculateWeights();
        created = true;
        return this;
    }

    private void calculateWeights()
    {
        int actualWeight = 0;

        for (SoundLine sound : sounds) { actualWeight += sound.getProbability(); }

        totalWeight = actualWeight;
    }

    public void playRandom()
    {
        if (!created) { /* */ throw new RuntimeException("Call .create() on your SoundLib!"); }

        int chances = (int) (Math.random() * (totalWeight)) + 1;

        for (SoundLine sound : sounds)
        {
            chances -= sound.getProbability();
            if (chances <= 0) {
                playSound(sound.getSoundCode());
                return;
            }
        }

    }

    public void playLocation(String soundLocation)
    {
        for (SoundLine sound : sounds)
        {
            if (sound.getSoundLocation().equals(soundLocation))
            {
                playSound(sound.getSoundCode());
                return;
            }
        }
    }

    private void playSound(int soundID)
    {
        player.startPlaying(
                hardwareMap.appContext, soundID
        );
    }


}
