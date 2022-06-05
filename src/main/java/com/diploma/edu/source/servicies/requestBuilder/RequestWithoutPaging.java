package com.diploma.edu.source.servicies.requestBuilder;

import java.util.Map;

public class RequestWithoutPaging extends RequestBuilder {

    public RequestWithoutPaging(Request request, Map<String, String> params){
        super(request, params);
    }

    @Override
    public void buildSelectBlock() {

    }

    @Override
    public void buildFilterBlock() {
        super.buildFilterBlock();
    }
}
