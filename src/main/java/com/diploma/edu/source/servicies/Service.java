package com.diploma.edu.source.servicies;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service<T> {

    T getById(Long id);

    boolean create(T object);

    boolean delete(Long id);

    boolean update(T object);

    Page<T> getAll(Pageable pageable, List<SearchCriteria> filter, SortCriteria sort);

}
