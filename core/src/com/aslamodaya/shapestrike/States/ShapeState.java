package com.aslamodaya.shapestrike.States;

/**
 * Created by naveed.shihab on 16/11/2016.
 */

public enum ShapeState {
    DIAMOND("diamond"), TRIANGLE("triangle"), HEXAGON("hexagon");

    private final String type;

    ShapeState(String type){
        this.type = type;
    }

    public String key(){
        return type;
    }
}
