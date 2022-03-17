package com.aimar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aimar.dao.InterfazDAO;

@SpringBootTest
class SpringbootAimarApplicationTests {

	@Autowired
	private InterfazDAO repository;

	public InterfazDAO getRepository() {
		return repository;
	}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(getRepository().findAll());
	}

}
