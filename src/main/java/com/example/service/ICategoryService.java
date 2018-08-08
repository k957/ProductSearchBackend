package com.example.service;

import java.util.List;

import com.example.dto.CategoryDto;
import com.example.model.Category;

public interface ICategoryService {
	Category create(CategoryDto categoryDto);

	List<Category> viewAll();

	Category viewOne(Long id);

	void delete(Category categoryList);
	
	List<Category> findByName(String name);
}
