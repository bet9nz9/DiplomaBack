package com.diploma.edu.source.model;

import java.math.BigInteger;

public enum ObjectTypes {
    LOGGER (new BigInteger("1")),
    ENTRANCE (new BigInteger("2")),
    EKEY(new BigInteger("3")),
    TYPE(new BigInteger("4")),
    ROLE(new BigInteger("5")),
    BUILDING(new BigInteger("6")),
    ADDRESS(new BigInteger("7")),
    USER(new BigInteger("8")),
    CONTACT(new BigInteger("9")),
    CONTACT_TYPE(new BigInteger("10")),
    NOTIFICATION(new BigInteger("11")),
    UTILITY(new BigInteger("12")),
    CATEGORY(new BigInteger("13")),
    SERVICES(new BigInteger("14"));

    private BigInteger objTypeId;

    ObjectTypes(BigInteger bigInteger) {

    }

    public BigInteger getObjTypeId(){
        return objTypeId;
    }
}
