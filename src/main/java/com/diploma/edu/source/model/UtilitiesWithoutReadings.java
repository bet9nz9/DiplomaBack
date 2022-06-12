package com.diploma.edu.source.model;

public enum UtilitiesWithoutReadings {
    INTERNET("Интернет"), OSMD("ОСМД");

    public String utilityName;

    UtilitiesWithoutReadings(String utilityName){
        this.utilityName = utilityName;
    }

    protected String getUtilityName(){
        return this.utilityName;
    }
}
