package com.diploma.edu.source.entranceInteraction;

public enum EntranceInteractionParams {

    ACTION("action"),
    ENTRANCE_ID("entranceId"),
    EKEY("ekeyId");

    private String param;

    EntranceInteractionParams(String param){
        this.param = param;
    }

    public String getParam() {
        return param;
    }

}
