package com.diploma.edu.source;

import com.diploma.edu.source.model.User;
import com.diploma.edu.source.servicies.requestBuilder.CountElementsRequest;
import com.diploma.edu.source.servicies.requestBuilder.Director;
import com.diploma.edu.source.servicies.requestBuilder.Request;
import com.diploma.edu.source.servicies.requestBuilder.RequestGetByID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RequestBuilderTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void countRequestTest(){
        String expectedRequest = "SELECT COUNT(*) FROM ( SELECT o.object_id id,\n" +
                " o.name name,\n" +
                " o.description description ,\n" +
                "email.VALUE email,\n" +
                "password.VALUE password,\n" +
                "firstName.VALUE firstName,\n" +
                "lastName.VALUE lastName,\n" +
                "patronymic.VALUE patronymic,\n" +
                "isActive.LIST_VALUE_ID isActive,\n" +
                "receiveUtilityNotification.LIST_VALUE_ID receiveUtilityNotification,\n" +
                "role.REFERENCE role,\n" +
                "activationCode.VALUE activationCode\n" +
                "FROM OBJECTS o \n" +
                "left join ATTRIBUTES email\n" +
                "on o.object_id = email.object_id and email.attr_id = 21\n" +
                "left join ATTRIBUTES password\n" +
                "on o.object_id = password.object_id and password.attr_id = 22\n" +
                "left join ATTRIBUTES firstName\n" +
                "on o.object_id = firstName.object_id and firstName.attr_id = 23\n" +
                "left join ATTRIBUTES lastName\n" +
                "on o.object_id = lastName.object_id and lastName.attr_id = 24\n" +
                "left join ATTRIBUTES patronymic\n" +
                "on o.object_id = patronymic.object_id and patronymic.attr_id = 25\n" +
                "left join ATTRIBUTES isActive\n" +
                "on o.object_id = isActive.object_id and isActive.attr_id = 26\n" +
                "left join ATTRIBUTES receiveUtilityNotification\n" +
                "on o.object_id = receiveUtilityNotification.object_id and receiveUtilityNotification.attr_id = 27\n" +
                "left join OBJREFERENCE role\n" +
                "on o.object_id = role.object_id and role.attr_id = 28\n" +
                "left join ATTRIBUTES activationCode\n" +
                "on o.object_id = activationCode.object_id and activationCode.attr_id = 55\n" +
                "WHERE o.object_type_id = 8\n" +
                ") WHERE 1=1 AND id = 11";

        Map<String, String> params2 = new HashMap<>();
        params2.put("id", "11");
        params2.put("page", "0");
        params2.put("size", "5");
        Class clazz = User.class;

        String resultRequest = Director.valueOf(clazz).getRequest(new CountElementsRequest(new Request(clazz), params2)).buildRequest();

        Assert.notNull(resultRequest, "Result request is null.");
        Assert.isEquals(expectedRequest, resultRequest, "Result request is wrong.");
    }

    @Test
    void buildRequestTest(){
        String expectedRequest = "select * from (select row_number() over (order by id ASC) rowRank, a.* from(\n" +
                "SELECT * FROM ( SELECT o.object_id id,\n" +
                " o.name name,\n" +
                " o.description description ,\n" +
                "email.VALUE email,\n" +
                "password.VALUE password,\n" +
                "firstName.VALUE firstName,\n" +
                "lastName.VALUE lastName,\n" +
                "patronymic.VALUE patronymic,\n" +
                "isActive.LIST_VALUE_ID isActive,\n" +
                "receiveUtilityNotification.LIST_VALUE_ID receiveUtilityNotification,\n" +
                "role.REFERENCE role,\n" +
                "activationCode.VALUE activationCode\n" +
                "FROM OBJECTS o \n" +
                "left join ATTRIBUTES email\n" +
                "on o.object_id = email.object_id and email.attr_id = 21\n" +
                "left join ATTRIBUTES password\n" +
                "on o.object_id = password.object_id and password.attr_id = 22\n" +
                "left join ATTRIBUTES firstName\n" +
                "on o.object_id = firstName.object_id and firstName.attr_id = 23\n" +
                "left join ATTRIBUTES lastName\n" +
                "on o.object_id = lastName.object_id and lastName.attr_id = 24\n" +
                "left join ATTRIBUTES patronymic\n" +
                "on o.object_id = patronymic.object_id and patronymic.attr_id = 25\n" +
                "left join ATTRIBUTES isActive\n" +
                "on o.object_id = isActive.object_id and isActive.attr_id = 26\n" +
                "left join ATTRIBUTES receiveUtilityNotification\n" +
                "on o.object_id = receiveUtilityNotification.object_id and receiveUtilityNotification.attr_id = 27\n" +
                "left join OBJREFERENCE role\n" +
                "on o.object_id = role.object_id and role.attr_id = 28\n" +
                "left join ATTRIBUTES activationCode\n" +
                "on o.object_id = activationCode.object_id and activationCode.attr_id = 55\n" +
                "WHERE o.object_type_id = 8\n" +
                ") WHERE 1=1 AND name = '1') a) where rowRank between 1 AND 5";

        Map<String, String> params = new HashMap<>();
        params.put("name", "1");
        params.put("page", "0");
        params.put("size", "5");

        Class clazz = User.class;

        String resultRequest = Director.valueOf(clazz).getRequest(params).buildRequest();

        Assert.notNull(resultRequest, "Result request is null.");
        Assert.isEquals(expectedRequest, resultRequest, "Result request is wrong.");
    }

    @Test
    void getByIdRequestTest(){
        final BigInteger id = new BigInteger("5");
        final Class clazz = User.class;
        final String expectedRequest = "\n" +
                "SELECT * FROM ( SELECT o.object_id id,\n" +
                " o.name name,\n" +
                " o.description description ,\n" +
                "email.VALUE email,\n" +
                "password.VALUE password,\n" +
                "firstName.VALUE firstName,\n" +
                "lastName.VALUE lastName,\n" +
                "patronymic.VALUE patronymic,\n" +
                "isActive.LIST_VALUE_ID isActive,\n" +
                "receiveUtilityNotification.LIST_VALUE_ID receiveUtilityNotification,\n" +
                "role.REFERENCE role,\n" +
                "activationCode.VALUE activationCode\n" +
                "FROM OBJECTS o \n" +
                "left join ATTRIBUTES email\n" +
                "on o.object_id = email.object_id and email.attr_id = 21\n" +
                "left join ATTRIBUTES password\n" +
                "on o.object_id = password.object_id and password.attr_id = 22\n" +
                "left join ATTRIBUTES firstName\n" +
                "on o.object_id = firstName.object_id and firstName.attr_id = 23\n" +
                "left join ATTRIBUTES lastName\n" +
                "on o.object_id = lastName.object_id and lastName.attr_id = 24\n" +
                "left join ATTRIBUTES patronymic\n" +
                "on o.object_id = patronymic.object_id and patronymic.attr_id = 25\n" +
                "left join ATTRIBUTES isActive\n" +
                "on o.object_id = isActive.object_id and isActive.attr_id = 26\n" +
                "left join ATTRIBUTES receiveUtilityNotification\n" +
                "on o.object_id = receiveUtilityNotification.object_id and receiveUtilityNotification.attr_id = 27\n" +
                "left join OBJREFERENCE role\n" +
                "on o.object_id = role.object_id and role.attr_id = 28\n" +
                "left join ATTRIBUTES activationCode\n" +
                "on o.object_id = activationCode.object_id and activationCode.attr_id = 55\n" +
                "WHERE o.object_type_id = 8\n" +
                ") WHERE 1=1 AND id = 5 ";

        String resultRequest = Director.valueOf(clazz).
                getRequest(new RequestGetByID(new Request(clazz), id)).
                buildRequest();

        Assert.notNull(resultRequest, "Result request is null.");
        Assert.isEquals(expectedRequest, resultRequest, "Result request is wrong.");
    }

}
