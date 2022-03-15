package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public abstract class RequestBuilder {

    protected Request request;
    protected List<SearchCriteria> filter;
    protected SortCriteria sort;
    protected Pageable pageable;

    public RequestBuilder(Request request, List<SearchCriteria> filter, SortCriteria sort, Pageable pageable) {
        this.request = request;
        if (filter == null || filter.size() <= 0)
            this.filter = new ArrayList<>();
        else this.filter = filter;
        if (sort != null){
            if (sort.getDirection() == null || sort.getProperty() == null){
                this.sort = null;
            }else {
                this.sort = sort;
            }
        }
        this.pageable = pageable;
    }

    public abstract void buildSelectBlock();

    public abstract void buildFilterBlock();

    public Request getRequest() {
        return request;
    }
}
