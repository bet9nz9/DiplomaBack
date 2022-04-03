package com.diploma.edu.source.servicies.requestBuilder;

public enum PreparedRequests {
    UPDATE_ATTRIBUTES("UPDATE ATTRIBUTES SET {0} = {1} WHERE ATTR_ID = {2} AND OBJECT_ID = {3}"),
    UPDATE_OBJECTS("UPDATE OBJECTS SET {0} = {1} WHERE OBJECT_ID = {2}"),
    UPDATE_REFERENCES("UPDATE OBJREFERENCE SET {0} = {1} WHERE ATTR_ID = {2} AND OBJECT_ID = {3}"),
    INSERT_OBJECTS("INSERT INTO OBJECTS(object_id, parent_id, object_type_id, name, description) VALUES ({0}, {1}, {2}, {3}, {4})"),
    INSERT_REFERENCES("INSERT INTO OBJREFERENCE(attr_id, reference, object_id) VALUES ({0}, {1}, {2})"),
    INSERT_ATTRIBUTES("INSERT INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id) VALUES ({0}, {1}, {2}, {3}, {4})"),

    GET_NEW_OBJECT_ID("SELECT OBJECTS_SEQ.NEXTVAL FROM dual"),
    GET_OBJECT_ID("SELECT object_id FROM objects WHERE object_id = {0}"),
    DELETE_OBJECT("DELETE FROM OBJECTS WHERE OBJECT_ID = {0}"),
    GET_EMAILS("SELECT VALUE FROM ATTRIBUTES WHERE ATTR_ID = 21"),
    GET_REFERENCED_OBJ_ID("SELECT reference FROM OBJREFERENCE WHERE attr_id = {0} AND object_id = {1}"),
    GET_LIST_VALUE_BY_ID("SELECT VALUE FROM LISTS WHERE LIST_VALUE_ID = {0}"),
    IS_CATEGORY_WITHOUT_NOTIFICATIONS("SELECT COUNT(*) FROM OBJREFERENCE WHERE ATTR_ID = 35 AND REFERENCE = {0}");

    private String request;

    PreparedRequests(String request){
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
