package com.diploma.edu.source.servicies;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.Map;

public interface Service<T> {

    T getById(BigInteger id);

    boolean create(T object);

    boolean delete(BigInteger id);

    boolean update(T object);

    Page<T> getAll(Map<String, String> params);

}
