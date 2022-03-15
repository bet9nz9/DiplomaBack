package com.diploma.edu.source.db.annotations;

import com.diploma.edu.source.model.BaseEntity;

import java.lang.reflect.Field;

public class Attr {
    public Integer id;
    public ValueType valueType;
    public Field field;
    public Class<? extends BaseEntity> clazz;

    public Attr(Integer id, ValueType valueType, Field field) {
        this.id = id;
        this.valueType = valueType;
        this.field = field;
    }
    public Attr(Integer id, ValueType valueType, Field field, Class<? extends BaseEntity> clazz) {
        this.id = id;
        this.valueType = valueType;
        this.field = field;
        this.clazz = clazz;
    }
}
