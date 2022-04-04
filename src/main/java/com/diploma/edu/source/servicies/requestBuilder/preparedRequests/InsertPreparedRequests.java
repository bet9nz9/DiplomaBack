package com.diploma.edu.source.servicies.requestBuilder.preparedRequests;

public enum InsertPreparedRequests {

    INSERT_OBJECTS("INSERT INTO OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES ({0}, {1}, {2}, {3}, {4})"),
    INSERT_REFERENCES("INSERT INTO OBJREFERENCE(attr_id, reference, object_id) VALUES ({0}, {1}, {2})"),
    INSERT_ATTRIBUTES("INSERT INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES ({0}, {1}, {2}, {3}, {4})");

    private String request;

    InsertPreparedRequests(String request){
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
