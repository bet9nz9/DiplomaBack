package com.diploma.edu.source.db.annotations;

import com.diploma.edu.source.model.BaseEntity;

import java.lang.reflect.Field;

public class Attr {
    private Integer id;
    private ValueType valueType;
    private Field field;
    private Class<? extends BaseEntity> clazz;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public void setValueType(ValueType valueType) {
        this.valueType = valueType;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Class<? extends BaseEntity> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends BaseEntity> clazz) {
        this.clazz = clazz;
    }
}
