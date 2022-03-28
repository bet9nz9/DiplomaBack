package com.diploma.edu.source.db.annotations;

public enum ValueType {
    BASE_VALUE("BASE_VALUE", "OBJECTS"),
    VALUE("VALUE", "ATTRIBUTES"),
    DATE_VALUE("DATE_VALUE", "ATTRIBUTES"),
    LIST_VALUE("LIST_VALUE_ID", "ATTRIBUTES"),
    REF_VALUE("REFERENCE", "OBJREFERENCE");

    ValueType(String valueType, String table) {
        this.valueType = valueType;
        this.table = table;
    }

    private String valueType;
    private String table;

    public String getValueType() {
        return valueType;
    }

    public String getTable() {
        return table;
    }
}
