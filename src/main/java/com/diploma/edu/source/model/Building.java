package com.diploma.edu.source.model;

import com.diploma.edu.source.db.annotations.Attribute;
import com.diploma.edu.source.db.annotations.ObjectType;
import com.diploma.edu.source.db.annotations.ValueType;

@ObjectType(id = 7)
public class Building extends BaseEntity {

    @Attribute(id = 15, valueType = ValueType.VALUE)
    protected String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
