package org.firstinspires.ftc.teamcode.sousutils.soundLibrary;

import com.qualcomm.ftccommon.SoundPlayer;

import org.firstinspires.ftc.robotcore.external.android.AndroidSoundPool;

import java.util.ArrayList;
import java.util.List;

public class SoundLib {
    private AndroidSoundPool soundPool;

    private List<SoundLine> sounds = new ArrayList<SoundLine>();

    private boolean preload = false;
    private static final long PRELOAD_TIMEOUT_MS = 250;

    private static final long PRELOAD_DELAY_MS = 25;
    public SoundLib()
    {
        soundPool = new AndroidSoundPool();
        init();
    }

    private void init()
    {
        soundPool.initialize(SoundPlayer.getInstance());
        soundPool.setLoop(0);
        soundPool.setVolume(1);
    }

    public SoundLib enablePreloading()
    {
        this.preload = true;
        return this;
    }

    public SoundLib setVolume(float volume) { soundPool.setVolume(volume); return this; }
    public SoundLib setVolume(double volume) { setVolume((float) volume); return this; }
    public SoundLib setVolume(int volume) { setVolume((float)volume); return this; }

    public SoundLib mute() { soundPool.setVolume(0); return this; }


    private void tryPreload(String soundFile)
    {
        boolean preloaded = soundPool.preloadSound(soundFile);

        long startedAt = System.currentTimeMillis();
        long endAt = startedAt + PRELOAD_TIMEOUT_MS;

        while (!preloaded && System.currentTimeMillis() < endAt)
        {
            preloaded = soundPool.preloadSound(soundFile);
            try {
                Thread.sleep(PRELOAD_DELAY_MS);
            } catch (InterruptedException ex) {
                break;
            }

        }
    }

    public SoundLib addSound(SoundLine soundLine)
    {
        sounds.add(soundLine);

        if (preload)
        {
            tryPreload(soundLine.getSoundName());
        }

        return this;
    }

    public SoundLib addSound(String soundName, int priority)
    {
        addSound(new SoundLine(soundName, priority));
        return this;
    }

    public SoundLib addSound(String soundName)
    {
        addSound(soundName, 1);
        return this;
    }

    public SoundLib addSounds(String... soundNames)
    {
        for (String soundName : soundNames) { addSound(soundName); }
        return this;
    }

    public SoundLib addSounds(SoundLine... soundLines)
    {
        for (SoundLine line : soundLines) { addSound(line); }
        return this;
    }

    private void playSound(String fileName)
    {
        // TODO: investigate preload on missing behavior
        // TODO: see if we can route resources rather than use onbot file uploads

        try {
            soundPool.play(fileName);
        } catch (IllegalStateException ex) {
            // file not found in resources
        }

    }


    public String play(String soundName)
    {
        for (SoundLine line : sounds) {
            if (soundName.equals(line.getSoundName()))
            {
                playSound(soundName);
                return soundName;
            }
        }
        return null;
    }

    public String playRandom()
    {
        int totalChance = 0;
        for (SoundLine line : sounds) { totalChance += line.getProbability(); }

        int chances = (int) (Math.random() * totalChance) + 1;
        for (SoundLine line : sounds)
        {
            chances -= line.getProbability();
            if (chances <= 0)
            {
                playSound(line.getSoundName());
                return line.getSoundName();
            }
        }
        return null;
    }


}
