package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.controllers.requestParams.PagingAndSortingParams;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class RequestWithPaging extends RequestBuilder {

    @Deprecated
    public RequestWithPaging(Request request, List<SearchCriteria> filter, SortCriteria sort, Pageable pageable) {
        super(request, filter, sort, pageable);
    }

    public RequestWithPaging(Request request, Map<String, String> params) {
        super(request, params);
    }


    @Override
    public void buildSelectBlock() {
        SortCriteria sort = new SortCriteria("id", "ASC");

        if (params.containsKey(PagingAndSortingParams.SORT.getParameterName())){
            sort = new SortCriteria(params.get(PagingAndSortingParams.SORT.getParameterName()));
        }

        request.setSelectBlock(new StringBuilder(MessageFormat.format(sortBlockWithPaging, sort.getProperty(), sort.getDirection(), request.getSelectBlock())));
    }

    @Override
    public void buildFilterBlock() {
        super.buildFilterBlock();

        Pageable pageable = null;
        if (!params.containsKey(PagingAndSortingParams.PAGE.getParameterName()) && params.containsKey(PagingAndSortingParams.SIZE.getParameterName())){
            pageable = PageRequest.of(0, new Integer(params.get(PagingAndSortingParams.SIZE.getParameterName())));
        }
        if (params.containsKey(PagingAndSortingParams.PAGE.getParameterName()) && params.containsKey(PagingAndSortingParams.SIZE.getParameterName())){
            pageable = PageRequest.of(new Integer(params.get(PagingAndSortingParams.PAGE.getParameterName())), new Integer(params.get(PagingAndSortingParams.SIZE.getParameterName())));
        }

        request.setFilterBlock(new StringBuilder(
                request.getFilterBlock() +
                        ") a) where rowRank between " +
                        (((pageable.getPageNumber()) * pageable.getPageSize())+1) +
                        " AND " + pageable.getPageSize() * (pageable.getPageNumber()+1)
        ));
    }
}
