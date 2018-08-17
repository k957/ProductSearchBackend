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
import com.example.dto.MerchantDto;
import com.example.service.IMerchantService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocalSearchDirectoryApplication.class)
public class MerchantControllerV2UnitTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;

	@Autowired
	private IMerchantService merchantService;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void testViewAll() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("kanav");
		merchant.setDisplayName("kkk");
		merchant.setMailId("kanav@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/merchant")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testViewOneGives200whenCorrectIdPassed() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("nikita");
		merchant.setDisplayName("nnn");
		merchant.setMailId("nikita@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/merchant/merchantId/4")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testViewOneGives404ResourceNotFoundwhenWrongIdPassed() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("nikita");
		merchant.setDisplayName("nn");
		merchant.setMailId("ni@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/merchant/merchantId/454")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(404, result.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testViewOneGives400OnHittingWrongUrl() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("nikita");
		merchant.setDisplayName("nnnnn");
		merchant.setMailId("nin@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/merchant/merchantId")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(404, result.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testViewByDisplayName() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("arpit");
		merchant.setDisplayName("aaa");
		merchant.setMailId("arpit@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/merchant/displayName/aaa")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testViewOneGives400ResourceNotFoundwhenWrongNamePassed() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("nikita");
		merchant.setDisplayName("kk");
		merchant.setMailId("kkn@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/merchant/merchantId/n")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(400, result.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testViewOneGives404NoNamePassed() {
		MerchantDto merchant = new MerchantDto();
		merchant.setName("nikita");
		merchant.setDisplayName("nnnnnnnn");
		merchant.setMailId("ninnnnnnn@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		merchantService.create(merchant);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/merchant/merchantId/")
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(404, result.getResponse().getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}