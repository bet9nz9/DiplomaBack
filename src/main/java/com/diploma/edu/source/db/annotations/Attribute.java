package com.diploma.edu.source.db.annotations;

import com.diploma.edu.source.model.BaseEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Attribute {
    public int id();
    public ValueType valueType() default ValueType.REF_VALUE;
    public Class<? extends BaseEntity> clazz() default BaseEntity.class;
}
