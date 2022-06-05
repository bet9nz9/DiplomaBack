package com.diploma.edu.source.servicies.requestBuilder.criteria;

import com.diploma.edu.source.servicies.requestBuilder.requestParams.PagingAndSortingParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public class PaginationCriteria {

    public static Pageable getPagination(Map<String, String> params){
        if (params == null){
            return null;
        }
        if (!params.containsKey(PagingAndSortingParams.PAGE.getParameterName()) && params.containsKey(PagingAndSortingParams.SIZE.getParameterName())){
            return PageRequest.of(0, Integer.valueOf(params.get(PagingAndSortingParams.SIZE.getParameterName())));
        }
        if (params.containsKey(PagingAndSortingParams.PAGE.getParameterName()) && params.containsKey(PagingAndSortingParams.SIZE.getParameterName())){
            return PageRequest.of(Integer.valueOf(params.get(PagingAndSortingParams.PAGE.getParameterName())), Integer.valueOf(params.get(PagingAndSortingParams.SIZE.getParameterName())));
        }
        return null;
    }

}
