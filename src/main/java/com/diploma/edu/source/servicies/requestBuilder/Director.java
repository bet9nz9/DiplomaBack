package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.servicies.requestBuilder.requestParams.PagingAndSortingParams;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SearchCriteria;
import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Хранит билдер для запроса и начальный запрос.
 *
 * При вызове метода buildRequest проверяет переменные и используя подходящий билдер строит запрос.
 *
 * Если фильтра нет, то он просто добавит ничего в блок фильтрции.
 * Если нет сортировки, то применяется стандартная сортировка по полю id, по возрастанию ASC
 * */

public class Director {

    protected RequestBuilder builder;
    protected Request request;

    private Director(Class clazz) {
        request = new Request(clazz);
    }

    private void setBuilder(RequestBuilder builder) {
        this.builder = builder;
    }

    public static Director valueOf(Class clazz){
        Director director = new Director(clazz);
        return director;
    }

    @Deprecated
    public Request getRequest(Pageable pageable, List<SearchCriteria> filters, SortCriteria sortCriteria) {

        if (pageable == null) {
            setBuilder(new RequestWithoutPaging(request, filters, sortCriteria));
        }
        else{
            setBuilder(new RequestWithPaging(request, filters, sortCriteria, pageable));
        }

        builder.buildSelectBlock();
        builder.buildFilterBlock();
        builder.buildSortBlock();
        return builder.getRequest();
    }

    public Request getRequest(Map<String, String> params) {

        if (params.containsKey(PagingAndSortingParams.PAGE.getParameterName()) || params.containsKey(PagingAndSortingParams.SIZE.getParameterName())){
            setBuilder(new RequestWithPaging(request, params));
        } else {
            setBuilder(new RequestWithoutPaging(request, params));
        }

        builder.buildSelectBlock();
        builder.buildFilterBlock();
        builder.buildSortBlock();
        return builder.getRequest();
    }



    public Request getRequest(RequestBuilder builder) {
        setBuilder(builder);

        this.builder.buildSelectBlock();
        this.builder.buildFilterBlock();

        return this.builder.getRequest();
    }
}
