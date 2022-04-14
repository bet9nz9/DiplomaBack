package com.diploma.edu.source.servicies.requestBuilder.requestParams;

public enum RequestParams {
    DATE("dateAndTime", RequestParamDataType.NUM), ID("id", RequestParamDataType.NUM),
    TEXT("text", RequestParamDataType.STRING), DATE_FROM("dateFrom", RequestParamDataType.DATE),
    DATE_TO("dateTo", RequestParamDataType.DATE), APPARTMENT_NUMBER("apartmentNumber", RequestParamDataType.STRING),
    NAME("name", RequestParamDataType.STRING), TITLE("title", RequestParamDataType.STRING),
    CATEGORY_ID("category", RequestParamDataType.NUM),
    CREATED_BY("createdBy", RequestParamDataType.NUM), FLAT("flat", RequestParamDataType.NUM),
    BUILDING_ID("building", RequestParamDataType.NUM),
    USER_ID("userId", RequestParamDataType.NUM), NUMBER("number", RequestParamDataType.NUM),
    KEY_CODE("keyCode", RequestParamDataType.STRING), IS_ACTIVE("isActive", RequestParamDataType.BOOLEAN),
    STATUS("status", RequestParamDataType.BOOLEAN),
    ROLE_ID("roleId", RequestParamDataType.NUM), VALUE("value", RequestParamDataType.STRING),
    EMAIL("email", RequestParamDataType.STRING), FIRST_NAME("firstName", RequestParamDataType.STRING),
    LAST_NAME("lastName", RequestParamDataType.STRING), PATRONYMIC("patronymic", RequestParamDataType.STRING),
    ADDRESS("address", RequestParamDataType.NUM),
    RECEIVE_UTILITY_NOTIFICATION("receiveUtilityNotification", RequestParamDataType.BOOLEAN),
    BANK_BOOK("bankBook", RequestParamDataType.NUM), CURRENT_MONTH_READING("currentMonthReading", RequestParamDataType.NUM),
    LAST_MONTH_READING("lastMonthReading", RequestParamDataType.NUM), SERVICE("service", RequestParamDataType.NUM),
    SERVICE_TYPE("serviceType", RequestParamDataType.NUM);

    private final String requestParam;
    private final RequestParamDataType dataType;

    RequestParams(String requestParam, RequestParamDataType dataType){
        this.requestParam = requestParam;
        this.dataType = dataType;
    }

    public String getRequestParam(){
        return this.requestParam;
    }

    public RequestParamDataType getDataType(){
        return this.dataType;
    }
}
