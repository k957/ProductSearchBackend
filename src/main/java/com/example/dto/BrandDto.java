package com.example.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class BrandDto {
	@NotNull(message="brand name cannot be null")
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
