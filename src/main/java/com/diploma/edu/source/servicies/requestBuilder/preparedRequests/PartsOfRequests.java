package com.diploma.edu.source.servicies.requestBuilder.preparedRequests;

public enum PartsOfRequests {

    FILTER_NUMBER_BLOCK (" AND {0} = {1}"),
    FILTER_STRING_BLOCK (" AND {0} = ''{1}''"),
    SORT_BLOCK (" order by {0} {1} "),
    SORT_BLOCK_WITH_PAGING ("select * from (select row_number() over (order by {0} {1}) rowRank, a.* from({2}");

    private String requestPart;

    PartsOfRequests(String requestPart){
        this.requestPart = requestPart;
    }

    public String getRequestPart() {
        return requestPart;
    }

}
