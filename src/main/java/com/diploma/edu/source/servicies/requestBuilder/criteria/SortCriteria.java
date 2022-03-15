package com.diploma.edu.source.servicies.requestBuilder.criteria;

/**
 * Класс кретериев фильтрации
 *
 * Хранит в себе:
 * property - поле фильтрации
 * direction - направление(может быть только ASC - по возрастанию, DESC - по убыванию)
 * */

public class SortCriteria {

    private String property;
    private String direction;

    public SortCriteria(String property, String direction) {
        this.property = property;
        this.direction = direction;
    }

    public SortCriteria(String sortParameters) {
        if (sortParameters != null){
            String params [] = sortParameters.split("\\:");
            if (params[0] == null)
                params[0] = "id";
            if (params[1]==null)
                params[1] = "ASC";
            property = params[0];
            direction = params[1];
        }
    }

    public String getProperty() {
        return property;
    }

    public String getDirection() {
        return direction;
    }
}
