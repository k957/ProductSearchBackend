package com.example.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.dto.CategoryDto;
import com.example.model.Category;

@Component
public class CategoryAssembler {
	
	public List<Category> createCategoryEntity(List<CategoryDto> categoryDtoList){
		List<Category> categoryList = new ArrayList<>();
		categoryDtoList.forEach(categoryDto -> {
			Category category = new Category();
			category.setName(categoryDto.getName());
			category.setParentId(categoryDto.getParentId());
			category.setFeaturedRank(categoryDto.getFeaturedRank());
			
			categoryList.add(category);
		});
		
		return categoryList;
	}
}
