package com.diploma.edu.source.controllers;

import com.diploma.edu.source.controllers.requestParams.PagingAndSortingParams;
import com.diploma.edu.source.controllers.requestParams.RequestParams;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetRequestParams {

    public static SortCriteria getSortCriteria(Map<String, String> params){
        if (params.containsKey(PagingAndSortingParams.SORT)){
            return new SortCriteria(params.get(PagingAndSortingParams.SORT));
        }
        return null;
    }

    public static Pageable getPageable(Map<String, String> params){
        Pageable pageable = null;
        if (!params.containsKey(PagingAndSortingParams.PAGE) && params.containsKey(PagingAndSortingParams.SIZE)){
            pageable = PageRequest.of(0, new Integer(params.get(PagingAndSortingParams.SIZE)));
        }
        if (params.containsKey(PagingAndSortingParams.PAGE) && params.containsKey(PagingAndSortingParams.SIZE)){
            pageable = PageRequest.of(new Integer(params.get(PagingAndSortingParams.PAGE)), new Integer(params.get(PagingAndSortingParams.SIZE)));
        }
        return null;
    }

    public static List<SearchCriteria> getFilters(Map<String, String> params){
        List<SearchCriteria> criteria = new ArrayList<>();

        for (RequestParams param: RequestParams.values()){
            if (params.containsKey(param)){
                criteria.add(new SearchCriteria(param.name(), params.get(param)));
            }
        }
        return criteria;
    }
}
