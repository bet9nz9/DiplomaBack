package com.diploma.edu.source.servicies.requestBuilder.preparedRequests;

public enum SelectPreparedRequests {

    GET_NEW_OBJECT_ID("SELECT OBJECTS_SEQ.NEXTVAL FROM dual"),
    GET_OBJECT_ID("SELECT object_id FROM objects WHERE object_id = {0}"),
    GET_EMAILS("SELECT VALUE FROM ATTRIBUTES WHERE ATTR_ID = 21"),
    GET_REFERENCED_OBJ_ID("SELECT reference FROM OBJREFERENCE WHERE attr_id = {0} AND object_id = {1}"),
    GET_LIST_VALUE_BY_ID("SELECT VALUE FROM LISTS WHERE LIST_VALUE_ID = {0}"),
    IS_CATEGORY_WITHOUT_NOTIFICATIONS("SELECT COUNT(*) FROM OBJREFERENCE WHERE ATTR_ID = 35 AND REFERENCE = {0}");

    private String request;

    SelectPreparedRequests(String request){
        this.request = request;
    }

    public String getRequest() {
        return request;
    }
}
