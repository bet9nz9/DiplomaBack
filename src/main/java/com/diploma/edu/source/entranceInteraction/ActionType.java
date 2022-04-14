package com.diploma.edu.source.entranceInteraction;

public enum ActionType {

    BLOCK("block"),
    UNBLOCK("unblock"),
    CLOSE("close"),
    OPEN("open");

    private String action;

    ActionType(String action){
        this.action = action;
    }

    public String getAction() {
        return action;
    }

}
