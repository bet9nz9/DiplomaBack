package com.diploma.edu.source.servicies.requestBuilder.requestParams;

public enum PagingAndSortingParams {

    PAGE("page"), SIZE("size"), SORT("sort");

    private final String parameterName;

    PagingAndSortingParams(String parameterName){
        this.parameterName = parameterName;
    }

    public String getParameterName(){
        return this.parameterName;
    }
}
