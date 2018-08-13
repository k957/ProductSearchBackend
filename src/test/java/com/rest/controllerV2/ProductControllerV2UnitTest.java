/*package com.rest.controllerV2;

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
import com.example.dto.BrandDto;
import com.example.dto.CategoryDto;
import com.example.dto.MerchantDto;
import com.example.dto.ProductDto;
import com.example.service.IBrandService;
import com.example.service.ICategoryService;
import com.example.service.IMerchantService;
import com.example.service.IProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocalSearchDirectoryApplication.class)
public class ProductControllerV2UnitTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;
	
	@Autowired
	private IBrandService brandService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IMerchantService merchantService;
	
	@Autowired
	private IProductService productService;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	public void createBrand() {
		BrandDto brandDto = new BrandDto();
		brandDto.setName("Marks & Spencers");
		brandDto.setDescription("Marks & Spencer Group plc is a major British multinational retailer headquartered in the City of Westminster, London.");
		brandService.create(brandDto);
	}
	
	public void createCategory() {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setName("dairy");
		categoryService.create(categoryDto);
	}
	
	public void createMerchant() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("kanav");
		merchant.setDisplayName("kkk");
		merchant.setMailId("kanav@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
	}
	
	public void createProduct() {
		ProductDto productDto = new ProductDto();
		productDto.setBrandId(1l);
		productDto.setCategoryId(2l);
		productDto.setColor("red");
		productDto.setDescription("rookie");
		productDto.setImageUrl("dh");
		productDto.setMerchantId(4l);
		productDto.setName("gym gloves");
		productDto.setRank(1);
		productDto.setSize("small");
		productService.create(productDto);
	}
	
	public void initiatorMethod() {
		createBrand();
		createCategory();
		createMerchant();
		createProduct();
	}
	
	public void initiatorMethodForCreate() {
		createBrand();
		createCategory();
		createMerchant();
	}
	
	@Test
	public void testViewByName() {
		initiatorMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/product/productname/gym gloves")
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
	public void testViewAll() {
		initiatorMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/product")
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
		initiatorMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/product/productId/5")
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
	public void testViewByMerchantId() {
		initiatorMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/product/merchantId/4")
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
		initiatorMethodForCreate();
		String productJson = "{\n" + 
				"	\"merchantId\":4,\n" + 
				"	\"name\":\"Cricket Bat\",\n" + 
				"	\"description\":\"Kookabura Bat\",\n" + 
				"	\"color\":\"Green & white\",\n" + 
				"	\"size\":\"short handle\",\n" + 
				"	\"brandId\":1,\n" + 
				"	\"categoryId\":2,\n" + 
				"	\"imageUrl\":\"https://rukminim1.flixcart.com/image/832/832/j391ifk0/bat/x/c/f/900-1100-harrow-kelvin-kb42-mdn-original-imaeagcfgb7xswe9.jpeg?q=70\"\n" + 
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v2/product").accept(MediaType.APPLICATION_JSON)
				.content(productJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(201, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testUpdate() {
		initiatorMethod();
		String productJson = "{\n" + 
				"	\"merchantId\":4,\n" + 
				"	\"name\":\"Cricket Bat\",\n" + 
				"	\"description\":\"Kookabura Bat\",\n" + 
				"	\"color\":\"Green & white\",\n" + 
				"	\"size\":\"short handle\",\n" + 
				"	\"brandId\":1,\n" + 
				"	\"categoryId\":2,\n" + 
				"	\"imageUrl\":\"https://rukminim1.flixcart.com/image/832/832/j391ifk0/bat/x/c/f/900-1100-harrow-kelvin-kb42-mdn-original-imaeagcfgb7xswe9.jpeg?q=70\"\n" + 
				"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v2/product/productId/5").accept(MediaType.APPLICATION_JSON)
				.content(productJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(201, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
*/