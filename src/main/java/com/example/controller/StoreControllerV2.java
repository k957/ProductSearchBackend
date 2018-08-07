package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.StoreDto;
import com.example.model.Store;
import com.example.service.IStoreService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/store")
public class StoreControllerV2 {
	
	@Autowired
	private IStoreService storeService;
	
	@GetMapping
	@ApiOperation(value="returns the list of all Stores available",response=Store.class)
	public ResponseEntity<?> viewAll() {
		
			List<Store> store = storeService.viewAll();
			HttpHeaders responseHeaders = new HttpHeaders();
			return new ResponseEntity<>(store, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/storeId/{id}")
	@ApiOperation(value="returns one Store whose ID provided in the URL",response=StoreDto.class)
	public ResponseEntity<?> viewOne(@PathVariable(value = "id") Long id) {
		
			Store store = storeService.viewOne(id);
			HttpHeaders responseHeaders = new HttpHeaders();
			return new ResponseEntity<>(store, responseHeaders, HttpStatus.OK);

	}
}
