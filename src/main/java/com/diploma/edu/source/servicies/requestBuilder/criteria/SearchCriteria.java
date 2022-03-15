package com.diploma.edu.source.servicies.requestBuilder.criteria;
/**
 * Класс критериев фильтрации
 *
 * Хранит в себе:
 * key - поле для фильтра
 * value - поиск по этому значению
 * */

public class SearchCriteria {

    private String key;
    private Object value;

    public SearchCriteria(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

}
