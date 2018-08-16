package com.rest.controllerV2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.LocalSearchDirectoryApplication;
import com.example.dto.CategoryDto;
import com.example.model.Category;
import com.example.service.ICategoryService;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocalSearchDirectoryApplication.class)
public class CategoryControllerV2UnitTest {
	
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;
	
	@Autowired
	private ICategoryService categoryService;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testViewAll() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("dairy");
		categoryService.create(categoryDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/category")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testViewOne() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("home");
		Category category = categoryService.create(categoryDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/category/categoryId/"+category.getId())
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		String categoryJson = "{\"name\":\"mens\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/category").accept(MediaType.APPLICATION_JSON)
				.content(categoryJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(201, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("food");
		Category category = categoryService.create(categoryDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/v2/category/deleteCategory/"+category.getId());
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testViewByName() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("sports");
		categoryService.create(categoryDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/category/categoryName/dairy")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
