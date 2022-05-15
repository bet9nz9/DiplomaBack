package com.diploma.edu.source;

import com.diploma.edu.source.servicies.requestBuilder.criteria.PaginationCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class PaginationParamsTest {

    private Map<String, String> paramsNull;
    private final Map<String, String> paramsWithoutPage = new HashMap<String, String>(){{
        put("size", "5");
    }};
    private final Map<String, String> params = new HashMap<String, String>(){{
        put("size", "5");
        put("page", "5");
    }};

    @Test
    public void getPaginationParamsTest(){
        Pageable expected = PageRequest.of(5, 5);
        Pageable actual = PaginationCriteria.getPagination(params);

        Assert.notNull(actual, "Result pagination is null.");
        Assert.isEquals(expected, actual, "Actual result is not equals to expected.");
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void nullPaginationTest(){
        Pageable actual = PaginationCriteria.getPagination(paramsNull);
        Assert.assertNull(actual, "Object is null.");
    }

    @Test
    public void defaultPagePaginationTest(){
        Pageable expected = PageRequest.of(0, 5);
        Pageable actual = PaginationCriteria.getPagination(paramsWithoutPage);

        Assert.notNull(actual, "Result pagination is null.");
        Assert.isEquals(expected, actual, "Actual result is not equals to expected.");
    }

}
