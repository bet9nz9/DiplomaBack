package com.diploma.edu.source.servicies.requestBuilder.mappers;


public class MapperDirector {
    private AttributeMapper mapper;

    public MapperDirector(AttributeMapper mapper){
        this.mapper = mapper;
    }

    public void mapObjectAttribute(Object object, Object value) throws IllegalAccessException {
        mapper.mapAttribute(object, value);
    }
}
