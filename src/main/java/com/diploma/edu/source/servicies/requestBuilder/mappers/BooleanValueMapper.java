package com.diploma.edu.source.servicies.requestBuilder.mappers;

import com.diploma.edu.source.db.annotations.Attr;

public class ListValueMapper extends AttributeMapper {

    public ListValueMapper(Attr attribute) {
        super(attribute);
    }

    @Override
    public void mapAttribute(Object object, Object value) throws IllegalAccessException {
        getAttribute().field.set(object, (Boolean) value);
    }
}
