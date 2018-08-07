package com.example.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.ProductDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Product;
import com.example.service.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/product")
@Api(value="Product Controller REST Endpoint",description="Product Controller API")
public class ProductControllerV2 {
	
	@Autowired
	private IProductService productservice;

	
	@GetMapping("/productname/{productName}")
	@ApiOperation(value="view product list by passing product name in query string",response=Product.class)
	public ResponseEntity<?> viewByName(@PathVariable("productName") String name){
		Product product = productservice.findByName(name);
		HttpHeaders responseHeader = new HttpHeaders();
		if(product!=null){
			return new ResponseEntity<>(product, responseHeader, HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException("Product", "Product Name", name);
		}
		
	}
	
	@GetMapping
	@ApiOperation(value="returns the list of all Products available",response=Product.class)
	public ResponseEntity<?> viewAll() {
			List<Product> product = productservice.viewAll();
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(product, responseHeader, HttpStatus.OK);
		}

	@GetMapping("/productId/{id}")
	@ApiOperation(value="returns one Product whose ID provided in the URL",response=Product.class)
	public ResponseEntity<?> viewOne(@PathVariable("id") Long id) {
			Product product = productservice.viewOne(id);
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(product, responseHeader, HttpStatus.OK);
	}
	
	@GetMapping("/merchantId/{id}")
	@ApiOperation(value="returns list of products belonging to merchant whose id provided in URL",response=Product.class)
	public ResponseEntity<?> viewByMerchantId(@PathVariable("id") Long merchantId){
		List<Product> product = productservice.findByMerchantId(merchantId);
		HttpHeaders responseHeader = new HttpHeaders();
		if(product!=null){
			return new ResponseEntity<>(product, responseHeader, HttpStatus.OK);
		}else {
			throw new ResourceNotFoundException("Product", "Product Name", merchantId);
		}
	}
	
	@PostMapping
	@ApiOperation(value="method to create Product",response=Product.class)
	public ResponseEntity<?> create(@Valid @RequestBody ProductDto productDto) {
			Product product = productservice.create(productDto);
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(product, responseHeader, HttpStatus.CREATED);
		}
}
