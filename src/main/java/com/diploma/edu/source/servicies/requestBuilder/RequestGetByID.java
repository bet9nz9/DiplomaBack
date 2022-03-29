package com.diploma.edu.source.servicies.requestBuilder;


import java.math.BigInteger;

public class RequestGetByID extends RequestBuilder {

    private BigInteger id;

    public RequestGetByID(Request request, BigInteger id) {
        super(request, null, null, null);
        this.id = id;
    }

    @Override
    public void buildSelectBlock() {

    }

    @Override
    public void buildFilterBlock() {
        request.setFilterBlock(new StringBuilder(
                request.getFilterBlock() + " AND id = " + id + " "
        ));
    }
}
