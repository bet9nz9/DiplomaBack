package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RequestWithPaging extends RequestBuilder {

    public RequestWithPaging(Request request, List<SearchCriteria> filter, SortCriteria sort, Pageable pageable) {
        super(request, filter, sort, pageable);
    }

    @Override
    public void buildSelectBlock() {
        if (sort == null) {
            sort = new SortCriteria("id", "ASC");
        }
        request.setSelectBlock(new StringBuilder(
                "select * from (select row_number() over (order by \"" + sort.getProperty() + "\"" +
                        sort.getDirection() + ") rowRank, a.* from(" + request.getSelectBlock()
        ));
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
        request.setFilterBlock(new StringBuilder(
                request.getFilterBlock() +
                        ") a) where rowRank between " +
                        (((pageable.getPageNumber()) * pageable.getPageSize())+1) +
                        " AND " + pageable.getPageSize() * (pageable.getPageNumber()+1)
        ));
    }
}
