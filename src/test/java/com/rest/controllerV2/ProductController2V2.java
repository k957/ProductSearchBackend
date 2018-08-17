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
import com.example.dto.CategoryDto;
import com.example.dto.MerchantDto;
import com.example.dto.ProductDto;
import com.example.model.Brand;
import com.example.model.Category;
import com.example.model.Merchant;
import com.example.model.Product;
import com.example.service.IBrandService;
import com.example.service.ICategoryService;
import com.example.service.IMerchantService;
import com.example.service.IProductService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LocalSearchDirectoryApplication.class)
public class ProductController2V2 {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mvc;
	
	@MockBean
	private IBrandService brandService;
	
	@MockBean
	private ICategoryService categoryService;
	
	@MockBean
	private IMerchantService merchantService;
	
	@MockBean
	private IProductService productService;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	BrandDto brandDto = new BrandDto();
	CategoryDto categoryDto = new CategoryDto();
	MerchantDto merchantDto = new MerchantDto();
	ProductDto productDto = new ProductDto();
	
	private Long brandId;
	private Long categoryId;
	private Long merchantId;
	private Long productId;
	
	public Long createBrand() {
		Brand brand = new Brand();
		brandDto.setName("Marks & Spencers");
		brandDto.setDescription("Marks & Spencer Group plc is a major British multinational retailer headquartered in the City of Westminster, London.");
		brand.setId(1l);
		brand.setName("Marks & Spencers");
		brand.setDescription("Marks & Spencer Group plc is a major British multinational retailer headquartered in the City of Westminster, London.");
		Mockito.when(brandService.create(brandDto)).thenReturn(brand);
		return brand.getId();
	}
	
	public Long createCategory() {
		Category category = new Category();
		categoryDto.setName("dairy");
		
		category.setId(2l);
		category.setName("dairy");
		Mockito.when(categoryService.create(categoryDto)).thenReturn(category);
		return category.getId();
	}
	
	public Long createMerchant() {
		Merchant merchant = new Merchant();
		
		merchantDto.setName("kanav");
		merchantDto.setDisplayName("kkk");
		merchantDto.setMailId("kanav@gmail.com");
		merchantDto.setStatus('A');
		merchantDto.setMobileNo("9999");
		
		
		merchant.setName("kanav");
		merchant.setDisplayName("kkk");
		merchant.setMailId("kanav@gmail.com");
		merchant.setStatus('A');
		merchant.setMobileNo("9999");
		Mockito.when(merchantService.createV2(merchantDto)).thenReturn(merchant);
		return merchant.getId();
		
	}
	
	public Long createProduct() {
		brandId=createBrand();
		productDto.setBrandId(brandId);
		categoryId=createCategory();
		productDto.setCategoryId(categoryId);
		productDto.setColor("red");
		productDto.setDescription("rookie");
		productDto.setImageUrl("dh");
		merchantId=createMerchant();
		productDto.setMerchantId(merchantId);
		productDto.setName("gym gloves");
		productDto.setRank(1);
		productDto.setSize("small");
		
		Product product = new Product();
		product.setId(4l);
		brandId=createBrand();
		productDto.setBrandId(brandId);
		categoryId=createCategory();
		productDto.setCategoryId(categoryId);
		productDto.setColor("red");
		productDto.setDescription("rookie");
		productDto.setImageUrl("dh");
		merchantId=createMerchant();
		productDto.setMerchantId(merchantId);
		productDto.setName("gym gloves");
		productDto.setRank(1);
		productDto.setSize("small");
		Mockito.when(productService.create(productDto)).thenReturn(product);
		return product.getId();
	}
	
	
	@Test
	public void testViewByNamegives404whenNameNotFound() {
		productId=createCategory();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v2/product/productname/"+productDto.getName())
				.contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mvc.perform(requestBuilder).andReturn();
			assertEquals(404, result.getResponse().getStatus());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}
