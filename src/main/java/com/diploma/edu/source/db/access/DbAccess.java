package com.diploma.edu.source.db.access;

import com.diploma.edu.source.model.BaseEntity;
import org.springframework.data.domain.Page;

import java.math.BigInteger;
import java.util.Map;

public interface DbAccess {
    <T extends BaseEntity> int update(T obj);
    <T extends BaseEntity> int insert(T obj);
    <T extends BaseEntity> Integer delete(Class<T> clazz, BigInteger id);
    <T extends BaseEntity> Page<T> selectPage(Class<T> clazz, Map<String, String> params);
    <T extends BaseEntity> T getById(Class<T> clazz, BigInteger id);

}
