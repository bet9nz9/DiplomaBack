package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;

import java.util.List;
import java.util.Map;

public class CountElementsRequest extends RequestBuilder {

    @Deprecated
    public CountElementsRequest(Request request, List<SearchCriteria> filter, SortCriteria sort) {
        super(request, filter, sort, null);
    }

    public CountElementsRequest(Request request, Map<String, String> params) {
        super(request, params);
    }

    @Override
    public void buildSelectBlock() {
        request.selectBlock.replace(0, 14, "SELECT COUNT(*) FROM");
    }

    @Override
    public void buildFilterBlock() {
        super.buildFilterBlock();
    }
}
