package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.controllers.requestParams.PagingAndSortingParams;
import com.diploma.edu.source.controllers.requestParams.RequestParamDataType;
import com.diploma.edu.source.controllers.requestParams.RequestParams;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Pageable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class RequestBuilder {

    protected Request request;
    protected List<SearchCriteria> filter;
    protected SortCriteria sort;
    protected Pageable pageable;
    protected Map<String, String> params;

    @Deprecated
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

    public RequestBuilder(Request request, Map<String, String> params) {
        this.request = request;
        this.params = params;
    }

    public abstract void buildSelectBlock();

    public void buildFilterBlock(){
        for (RequestParams param: RequestParams.values()){
            if (params.containsKey(param.getRequestParam())){
                if (param.getDataType().equals(RequestParamDataType.NUM)){
                    request.filterBlock.append(MessageFormat.format(filterNumberBlock, param.getRequestParam(), params.get(param.getRequestParam())));
                } else {
                    request.filterBlock.append(MessageFormat.format(filterStringBlock, param.getRequestParam(), params.get(param.getRequestParam())));
                }
            }
        }
    };

    public void buildSortBlock(){
        if (params.containsKey(PagingAndSortingParams.SORT.getParameterName())){

            SortCriteria sortCriteria = new SortCriteria(params.get(PagingAndSortingParams.SORT.getParameterName()));

            request.setFilterBlock(new StringBuilder(
                    request.filterBlock.toString() + MessageFormat.format(sortBlock, sortCriteria.getProperty(), sortCriteria.getDirection())
            ));
        }
    }

    public Request getRequest() {
        return request;
    }

    protected String filterNumberBlock = " AND {0} = {1}";
    protected String filterStringBlock = " AND {0} = ''{1}''";
    protected String sortBlock = " order by {0} {1} ";
    protected String sortBlockWithPaging = "select * from (select row_number() over (order by {0} {1}) rowRank, a.* from({2}";
}
