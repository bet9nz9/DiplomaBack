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
            attribute.field.set(object, null);
        } else {
            attribute.field.set(object, new Date(new Long(value.toString())));
        }
    }
}
