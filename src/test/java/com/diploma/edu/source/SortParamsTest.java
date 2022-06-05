package com.diploma.edu.source;

import com.diploma.edu.source.servicies.requestBuilder.criteria.SortCriteria;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SortParamsTest {

    @Test
    public void buildSortParams(){
        SortCriteria expected = new SortCriteria("id", "ASC");
        SortCriteria actual = new SortCriteria("id:ASC");

        Assert.notNull(actual, "Object is null.");
        Assert.isEquals(expected.getDirection(), actual.getDirection(), "Actual direction param is incorrect.");
        Assert.isEquals(expected.getProperty(), actual.getProperty(), "Actual property param is incorrect.");
    }

}
