package com.diploma.edu.source.servicies.requestBuilder.preparedRequests;

public enum UpdatePreparedRequests {

    UPDATE_ATTRIBUTES("UPDATE ATTRIBUTES SET {0} = {1} WHERE ATTR_ID = {2} AND OBJECT_ID = {3}"),
    UPDATE_OBJECTS("UPDATE OBJECTS SET {0} = {1} WHERE OBJECT_ID = {2}"),
    UPDATE_REFERENCES("UPDATE OBJREFERENCE SET {0} = {1} WHERE ATTR_ID = {2} AND OBJECT_ID = {3}");

    private String request;

    UpdatePreparedRequests(String request){
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
