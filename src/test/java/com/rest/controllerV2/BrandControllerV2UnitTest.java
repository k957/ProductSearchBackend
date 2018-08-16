package com.rest.controllerV2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.LocalSearchDirectoryApplication;
import com.example.dto.BrandDto;
import com.example.service.IBrandService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocalSearchDirectoryApplication.class)
public class BrandControllerV2UnitTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;
	
	@MockBean
	private IBrandService brandService;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testViewAllBrandsGive201Status() {
		BrandDto brandDto = new BrandDto();
		brandDto.setName("Marks & Spencers");
		brandDto.setDescription("Marks & Spencer Group plc is a major British multinational retailer headquartered in the City of Westminster, London.");
		Mockito.when(brandService.create(brandDto));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/brand")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			System.out.println(result.getResponse().getContentAsString());
			assertEquals(200, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testCreate() {
		String brandJson = "{\"name\":\"Kookabura\",\n" + 
				"	\"description\":\"Kookaburra is an Australian sports equipment company, specialising in cricket and field hockey equipment, named after the Australian kingfisher.\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/brand").accept(MediaType.APPLICATION_JSON)
				.content(brandJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(201, result.getResponse().getStatus());
	}catch (Exception e) {
		e.printStackTrace();
		}
	}

}
