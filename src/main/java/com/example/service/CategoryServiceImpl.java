package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assembler.CategoryAssembler;
import com.example.dto.CategoryDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Category;
import com.example.repository.ICategoryRepository;

@Service
public class CategoryServiceImpl implements ICategoryService{
	
	@Autowired
	private CategoryAssembler categoryAssembler;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Override
	public Category create(CategoryDto categoryDto) {
		Category category = categoryAssembler.createCategoryEntity(categoryDto);
		category.setCreatedAt(new Date());
		categoryRepository.save(category);
		return category;
	}

	@Override
	public List<Category> viewAll() {
		List<Category> category = categoryRepository.findAll();
		return category;
	}

	@Override
	public Category viewOne(Long id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", id));
		return category;
	}

	@Override
	public void delete(Category category) {
		categoryRepository.delete(category);
	}

	@Override
	public List<Category> findByName(String name) {
		List<Category> category = categoryRepository.findByName(name);
		return category;
	}
	
}
