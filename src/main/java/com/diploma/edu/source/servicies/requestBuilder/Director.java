package com.diploma.edu.source.servicies.requestBuilder;

import com.diploma.edu.source.model.BaseEntity;
import com.diploma.edu.source.servicies.requestBuilder.criteria.PaginationCriteria;

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

    private Director(Class<? extends BaseEntity> clazz) {
        request = new Request(clazz);
    }

    private void setBuilder(RequestBuilder builder) {
        this.builder = builder;
    }

    public static Director valueOf(Class<? extends BaseEntity> clazz){
        return new Director(clazz);
    }

    public Request getRequest(Map<String, String> params) {

        if (PaginationCriteria.getPagination(params) == null){
            setBuilder(new RequestWithoutPaging(request, params));
        } else {
            setBuilder(new RequestWithPaging(request, params));
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
