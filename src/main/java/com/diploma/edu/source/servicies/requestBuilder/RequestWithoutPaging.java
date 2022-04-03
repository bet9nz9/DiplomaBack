package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;

import java.util.List;
import java.util.Map;

public class RequestWithoutPaging extends RequestBuilder {


    @Deprecated
    public RequestWithoutPaging(Request request, List<SearchCriteria> filter, SortCriteria sort) {
        super(request, filter, sort, null);
    }

    public RequestWithoutPaging(Request request, Map<String, String> params){
        super(request, params);
    }

    @Override
    public void buildSelectBlock() {

    }

    @Override
    public void buildFilterBlock() {
        super.buildFilterBlock();
    }
}
