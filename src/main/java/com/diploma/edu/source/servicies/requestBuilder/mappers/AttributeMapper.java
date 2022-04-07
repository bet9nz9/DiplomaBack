package com.diploma.edu.source.servicies.requestBuilder.mappers;

import com.diploma.edu.source.db.annotations.Attr;

import java.math.BigDecimal;
import java.math.BigInteger;

public class AttributeMapper {
    protected Attr attribute;

    public AttributeMapper(Attr attribute) {
        this.attribute = attribute;
    }

    public void mapAttribute(Object object, Object value) throws IllegalAccessException {
        if (value == null) {
            attribute.field.set(object, null);
        } else if (attribute.field.getType().equals(BigInteger.class)) {
            attribute.field.set(object, new BigInteger(value.toString()));
        } else if (attribute.field.getType().equals(BigDecimal.class)) {
            attribute.field.set(object, new BigDecimal(value.toString()));
        } else {
            attribute.field.set(object, value);
        }
    }
}
