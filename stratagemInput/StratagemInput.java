package org.firstinspires.ftc.teamcode.sousutils.stratagemInput;

public class StratagemInput {

    private String key;
    private StratagemStep[] sequence;

    private StratagemInputs treeRef = null;

    public StratagemInput(String name, StratagemStep... steps)
    {
        this.key = name;
        this.sequence = steps;
    }

    public void connect(StratagemInputs tree)
    {
        this.treeRef = tree;
    }

    
}
