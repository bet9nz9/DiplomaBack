package com.diploma.edu.source.controllers.requestParams;

public enum RequestParams {

    DATE("dateAndTime"), TEXT("text"), DATE_FROM("dateFrom"),
    DATE_TO("dateTo"), NAME("name"), TITLE("title"),
    CATEGORY_ID("categoryId"), CREATED_BY("createdBy"), FLAT("flat"),
    BUILDING_ID("buildingId"), USER_ID("userId"), NUMBER("number"),
    KEY_CODE("keyCode"), IS_ACTIVE("isActive"), STATUS("status"),
    ROLE_ID("roleId"), VALUE("value"), EMAIL("email"), FIRST_NAME("firstName"),
    LAST_NAME("lastName"), PATRONYMIC("patronymic"), ADDRESS("address"),
    RECEIVE_UTILITY_NOTIFICATION("receiveUtilityNotification"),
    BANK_BOOK("bankBook"), CURRENT_MONTH_READING("currentMonthReading"),
    LAST_MONTH_READING("lastMonthReading"), SERVICE("service");

    private final String requestParam;

    RequestParams(String requestParam){
        this.requestParam = requestParam;
    }

    public String getRequestParam(){
        return this.requestParam;
    }
}
