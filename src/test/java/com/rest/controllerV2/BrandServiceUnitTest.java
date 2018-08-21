package com.rest.controllerV2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.LocalSearchDirectoryApplication;
import com.example.model.Brand;
import com.example.repository.IBrandRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=LocalSearchDirectoryApplication.class)
public class BrandServiceUnitTest {

	@MockBean
	private IBrandRepository brandRepository; 
	
	List<Brand> brandList = new ArrayList<>();
	Brand brand = new Brand();
	@Before
	public void setUp() {
		
		brand.setId(1l);
		brand.setName("koma");
		brand.setDescription("dhf");
	
		brandList.add(brand);
		
	}
	@Test
	public void testCreate() {
		
	}

	@Test
	public void testViewAll() throws Exception {
		Mockito.when(brandRepository.findAll()).thenReturn(brandList);
		assertEquals(brand, brandList.get(0));
	}

	@Test
	public void testViewOne() {
		Mockito.when(brandRepository.getOne(brand.getId())).thenReturn(brand);
		assertEquals(brand.getName(), "koma");
		assertEquals(brand.getDescription(),"dhf" );
	}

	@Test
	public void testDelete() {
	}

}
