package com.diploma.edu.source.servicies.requestBuilder.mappers;

import com.diploma.edu.source.db.annotations.Attr;

public class BooleanValueMapper extends AttributeMapper {

    public BooleanValueMapper(Attr attribute) {
        super(attribute);
    }

    @Override
    public void mapAttribute(Object object, Object value) throws IllegalAccessException {
        if (value == null) {
            attribute.getField().set(object, null);
        } else {
            attribute.getField().set(object, Boolean.valueOf(value.toString()));
        }
    }
}
