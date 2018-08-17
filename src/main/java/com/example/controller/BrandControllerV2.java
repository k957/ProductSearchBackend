package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BrandDto;
import com.example.model.Brand;
import com.example.service.IBrandService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/brand")
@Api(value="Brand Controller REST Endpoint",description="Brand Controller API")
public class BrandControllerV2 {
	
	
	
	@Autowired
	private IBrandService brandService;
	
	@CrossOrigin
	@GetMapping
	@ApiOperation(value="returns list of all brands",response=Brand.class)
	public ResponseEntity<List<Brand>> viewAll(){
		List<Brand> brandList = brandService.viewAll();
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(brandList,headers,HttpStatus.OK);
	}
	 
	@PostMapping
	@ApiOperation(value="method to create brands",response=Brand.class)
	public ResponseEntity<Brand> create(@Valid @RequestBody BrandDto brandDto){
		Brand brand = brandService.create(brandDto);
		HttpHeaders responseHeader = new HttpHeaders();
		return new ResponseEntity<>(brand,responseHeader,HttpStatus.CREATED);
	}
}
