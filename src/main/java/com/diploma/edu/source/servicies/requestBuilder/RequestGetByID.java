package com.diploma.edu.source.servicies.requestBuilder;


public class RequestGetByID extends RequestBuilder {

    Long id;

    public RequestGetByID(Request request, Long id) {
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
