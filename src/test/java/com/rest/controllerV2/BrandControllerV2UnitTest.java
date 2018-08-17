package com.rest.controllerV2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
import com.example.model.Brand;
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
		Brand brand = new Brand();
		brand.setId(1L);
		brand.setName("Marks & Spencers");
		brand.setDescription("Marks & Spencer Group plc is a major British multinational retailer headquartered in the City of Westminster, London.");

		List<Brand> brandList = new ArrayList<>();
		brandList.add(brand);
		
		brandDto.setName("Marks & Spencers");
		brandDto.setDescription("Marks & Spencer Group plc is a major British multinational retailer headquartered in the City of Westminster, London.");
		Mockito.when(brandService.create(brandDto)).thenReturn(brand);
		Mockito.when(brandService.viewAll()).thenReturn(brandList);
		
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

	@Test
	public void testCreateBadJsongivesErrorStatus400() {
		String brandJson = "{\"name\":\"Kookabura\",\n" ;
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/brand").accept(MediaType.APPLICATION_JSON)
				.content(brandJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(400, result.getResponse().getStatus());
	}catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateWhenNameisNullGivesError() {
		String brandJson = "{\"name\":null,\n" + 
				"	\"description\":\"Kookaburra is an Australian sports equipment company, specialising in cricket and field hockey equipment, named after the Australian kingfisher.\"}";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/brand").accept(MediaType.APPLICATION_JSON)
				.content(brandJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(400, result.getResponse().getStatus());
	}catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateEmptyJsonGivesError() {
		String brandJson = "{ }";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/brand").accept(MediaType.APPLICATION_JSON)
				.content(brandJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(400, result.getResponse().getStatus());
	}catch (Exception e) {
		e.printStackTrace();
		}
	}
}
