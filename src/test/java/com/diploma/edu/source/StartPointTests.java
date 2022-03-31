package com.diploma.edu.source;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.Date;

@SpringBootTest
class StartPointTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void testDateInDataBase(){
		String insertRequest = "INSERT INTO TESTDATE(DATEANDTIME) VALUES({0})";
		String getRequest = "GET * FROM TESTDATE";


		Date date = new Date();
		BigInteger dateValue = BigInteger.valueOf(date.getTime());
		jdbcTemplate.update(MessageFormat.format(insertRequest, BigInteger.valueOf(date.getTime()).toString()));

	}

}
