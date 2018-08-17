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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CategoryDto;
import com.example.model.Category;
import com.example.service.ICategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v2/category")
@Api(value="Category Controller REST Endpoint",description="Category Controller API")
public class CategoryControllerV2 {
	
	@Autowired
	private ICategoryService categoryService;
	
	@CrossOrigin
	@GetMapping
	@ApiOperation(value="returns list of all categories",response=Category.class)
	public ResponseEntity<?> viewAll(){
		List<Category> category = categoryService.viewAll(); 
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(category,responseHeaders,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/categoryId/{id}")
	@ApiOperation(value="returns one category whose id provided in url",response=Category.class)
	public ResponseEntity<?> viewOne(@PathVariable("id") Long id){
		Category category = categoryService.viewOne(id);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(category,responseHeaders,HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value="creates category",response=Category.class)
	public ResponseEntity<?> create(@Valid @RequestBody CategoryDto categoryDto){
		Category category = categoryService.create(categoryDto);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(category,responseHeaders,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteCategory/{id}")
	@ApiOperation(value="deletes category whose id provided in url")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		Category category = categoryService.viewOne(id);
		categoryService.delete(category);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/categoryName/{name}")
	@ApiOperation(value="returns list of category whose name provided in url",response=Category.class)
	public ResponseEntity<?> viewByName(@PathVariable("name") String categoryName){
		List<Category> category = categoryService.findByName(categoryName);
		HttpHeaders responseHeaders = new HttpHeaders();
		return new ResponseEntity<>(category,responseHeaders,HttpStatus.OK);
	}
}
