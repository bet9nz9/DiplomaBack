package com.diploma.edu.source.servicies.requestBuilder.preparedRequests;

public enum DeletePreparedRequests {

    DELETE_OBJECT("DELETE FROM OBJECTS WHERE OBJECT_ID = {0}");

    private String request;

    DeletePreparedRequests(String request){
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
