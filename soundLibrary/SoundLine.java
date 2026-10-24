package org.firstinspires.ftc.teamcode.sousutils.soundLibrary;


class SoundLine {
    private final String soundName; // includes file extension (.wav, .mp3)
    private final int probability;


    public SoundLine(String soundName, int probability)
    {
        this.soundName = soundName;
        this.probability = probability;
    }

    public SoundLine(String soundName)
    {
        this(soundName, 1);
    }

    protected String getSoundName() { return soundName; }
    protected int getProbability() { return probability; }

}
