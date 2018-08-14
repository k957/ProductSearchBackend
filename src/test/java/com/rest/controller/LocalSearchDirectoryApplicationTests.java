package com.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.LocalSearchDirectoryApplication;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {LocalSearchDirectoryApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocalSearchDirectoryApplicationTests {
	@Test
	public void contextLoads() {
	}
	@Test
	public void canaryTest() {
		assert(true);
	}
	
	@Test
	public void craetestoreTest() {
		assert(true);
	}
}
