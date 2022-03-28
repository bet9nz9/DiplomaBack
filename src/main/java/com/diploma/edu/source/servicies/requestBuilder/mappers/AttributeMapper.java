package com.diploma.edu.source.servicies.requestBuilder.mappers;

import com.diploma.edu.source.db.annotations.Attr;

import java.math.BigDecimal;

public class AttributeMapper {
    protected Attr attribute;

    public AttributeMapper(Attr attribute){
        this.attribute = attribute;
    }

    public void mapAttribute(Object object, Object value) throws IllegalAccessException {
        if (value instanceof BigDecimal){
            attribute.field.set(object, new Long(value.toString()));
        }else {
            attribute.field.set(object, value);
        }
    }
}
