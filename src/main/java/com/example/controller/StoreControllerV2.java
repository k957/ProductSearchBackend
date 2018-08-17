package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@CrossOrigin
	@GetMapping
	@ApiOperation(value="returns the list of all Stores available",response=Store.class)
	public ResponseEntity<?> viewAll() {
		
			List<Store> store = storeService.viewAll();
			HttpHeaders responseHeaders = new HttpHeaders();
			return new ResponseEntity<>(store, responseHeaders, HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/storeId/{id}")
	@ApiOperation(value="returns one Store whose ID provided in the URL",response=Store.class)
	public ResponseEntity<?> viewOne(@PathVariable(value = "id") Long id) {
		
			Store store = storeService.viewOne(id);
			HttpHeaders responseHeaders = new HttpHeaders();
			return new ResponseEntity<>(store, responseHeaders, HttpStatus.OK);

	}
	
	@CrossOrigin
	@GetMapping("/storeName/{name}")
	@ApiOperation(value="returns list of store whose name provided in URL",response=Store.class)
	public ResponseEntity<Object> viewByName(@PathVariable("name") String name){
		
		List<Store> store = storeService.findByName(name);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(store,headers,HttpStatus.OK);
	}
	
	@PutMapping("/storeId/{id}")
	@ApiOperation(value="updates store whose id provided in URL",response=Store.class)
	public ResponseEntity<Store> update(@Valid @RequestBody StoreDto storeDto, @PathVariable(value = "id") Long id) {
			Store updatedStore = storeService.update(storeDto, id);
			HttpHeaders responseHeader = new HttpHeaders();
			return new ResponseEntity<>(updatedStore, responseHeader, HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/storeId/{id}")
	@ApiOperation(value="deletes store whose id provided in URL")
	public ResponseEntity<Object> delete(@PathVariable(value="id") Long id){
		storeService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	
}
