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
            attribute.getField().set(object, null);
        } else if (attribute.getField().getType().equals(BigInteger.class)) {
            attribute.getField().set(object, new BigInteger(value.toString()));
        } else if (attribute.getField().getType().equals(BigDecimal.class)) {
            attribute.getField().set(object, new BigDecimal(value.toString()));
        } else {
            attribute.getField().set(object, value);
        }
    }
}
