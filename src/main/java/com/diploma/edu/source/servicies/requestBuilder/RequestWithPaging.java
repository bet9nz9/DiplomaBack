package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.servicies.requestBuilder.criteria.PaginationCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import com.diploma.edu.source.servicies.requestBuilder.preparedRequests.PartsOfRequests;
import com.diploma.edu.source.servicies.requestBuilder.requestParams.PagingAndSortingParams;
import org.springframework.data.domain.Pageable;

import java.text.MessageFormat;
import java.util.Map;

public class RequestWithPaging extends RequestBuilder {

    public RequestWithPaging(Request request, Map<String, String> params) {
        super(request, params);
    }

    @Override
    public void buildSelectBlock() {
        SortCriteria sort = new SortCriteria("id", "ASC");

        if (params.containsKey(PagingAndSortingParams.SORT.getParameterName())){
            sort = new SortCriteria(params.get(PagingAndSortingParams.SORT.getParameterName()));
        }

        request.setSelectBlock(new StringBuilder(MessageFormat.format(PartsOfRequests.SORT_BLOCK_WITH_PAGING.getRequestPart(), sort.getProperty(), sort.getDirection(), request.getSelectBlock())));
    }

    @Override
    public void buildFilterBlock() {
        super.buildFilterBlock();

        Pageable pageable = PaginationCriteria.getPagination(params);

        //TODO: попробовать переделать расчет
        request.setFilterBlock(new StringBuilder(
                request.getFilterBlock() +
                        ") a) where rowRank between " +
                        (((pageable.getPageNumber()) * pageable.getPageSize())+1) +
                        " AND " + pageable.getPageSize() * (pageable.getPageNumber()+1)
        ));
    }
}
