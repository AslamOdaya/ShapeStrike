package com.aslamodaya.shapestrike.States;

/**
 * Created by naveed.shihab on 16/11/2016.
 */

public enum ColourState {
    RED("red"), GREEN("green"), BLUE("blue"), MAGENTA("magenta");

    private final String type;

    ColourState(String type){
        this.type = type;
    }

    public String key(){
        return type;
    }
}
