package com.diploma.edu.source.servicies.requestBuilder.mappers;

import com.diploma.edu.source.db.annotations.Attr;

import java.util.Date;

public class DateValueMapper extends AttributeMapper {

    public DateValueMapper(Attr attribute) {
        super(attribute);
    }

    @Override
    public void mapAttribute(Object object, Object value) throws IllegalAccessException {
        if (value == null) {
            attribute.getField().set(object, null);
        } else {
            attribute.getField().set(object, new Date(Long.valueOf(value.toString())));
        }
    }
}
