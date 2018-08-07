package com.example.dto;

import java.util.List;

public class BrandDto {
	
	private String name;
	private String description;
	private List<ProductDto> productDto;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ProductDto> getProductDto() {
		return productDto;
	}
	public void setProductDto(List<ProductDto> productDto) {
		this.productDto = productDto;
	}
	
	
}
