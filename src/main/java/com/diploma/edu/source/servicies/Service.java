package com.diploma.edu.source.servicies;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface Service<T> {

    T getById(BigInteger id);

    boolean create(T object);

    boolean delete(BigInteger id);

    boolean update(T object);

    Page<T> getAll(Pageable pageable, List<SearchCriteria> filter, SortCriteria sort);

}
