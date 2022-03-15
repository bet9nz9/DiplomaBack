package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;

import java.util.List;

public class CountElementsRequest extends RequestBuilder {

    public CountElementsRequest(Request request, List<SearchCriteria> filter, SortCriteria sort) {
        super(request, filter, sort, null);
    }

    @Override
    public void buildSelectBlock() {
        request.selectBlock.replace(0, 14, "SELECT COUNT(*) FROM");
    }

    @Override
    public void buildFilterBlock() {
        for (SearchCriteria criteria : filter) {
            if (criteria.getKey().equals("month")){
                request.filterBlock.append(" AND to_date(to_char(\"date\", 'MM yyyy'),'MM yyyy') = to_date('" + criteria.getValue() + "', 'yyyy MM') ");
            }else {
                request.filterBlock.append(" AND \"" + criteria.getKey() + "\" " + criteria.getValue() + " ");
            }
        }
    }
}
