package com.diploma.edu.source.entranceInteraction;

public enum ActionMessage {

    BLOCK_UNBLOCK_MESSAGE("Статус изменен, entranceId = {0}, isActive = {1}."),
    OPEN_CLOSE_MESSAGE("Действие {0}, entranceId = {1}, ekeyId = {2}.");

    private String message;

    ActionMessage(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
