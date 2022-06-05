package com.diploma.edu.source.servicies.requestBuilder;

import java.util.Map;

public class CountElementsRequest extends RequestBuilder {

    public CountElementsRequest(Request request, Map<String, String> params) {
        super(request, params);
    }

    @Override
    public void buildSelectBlock() {
        request.selectBlock.replace(0, 14, "SELECT COUNT(*) FROM");
    }

    @Override
    public void buildFilterBlock() {
        super.buildFilterBlock();
    }
}
