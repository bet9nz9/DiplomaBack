package com.diploma.edu.source.model;

import java.math.BigInteger;

public enum ListValues {
    TRUE(new BigInteger("1"), "true"),
    FALSE(new BigInteger("2"), "false");

    private BigInteger listValueId;
    private String value;

    ListValues(BigInteger listValueId, String value){
        this.listValueId = listValueId;
        this.value = value;
    }

    public static BigInteger getListValueIdByValue(Object value){
        for (ListValues listValue : ListValues.values()){
            if (listValue.value.equals(value)){
                return listValue.listValueId;
            }
        }
        return null;
    }
}
