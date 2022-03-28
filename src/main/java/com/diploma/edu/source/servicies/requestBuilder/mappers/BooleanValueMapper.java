package com.diploma.edu.source.servicies.requestBuilder.mappers;

import com.diploma.edu.source.db.annotations.Attr;

public class BooleanValueMapper extends AttributeMapper {

    public BooleanValueMapper(Attr attribute) {
        super(attribute);
    }

    @Override
    public void mapAttribute(Object object, Object value) throws IllegalAccessException {
        if (value == null) {
            attribute.field.set(object, null);
        } else {
            attribute.field.set(object, new Boolean(value.toString()));
        }
    }
}
