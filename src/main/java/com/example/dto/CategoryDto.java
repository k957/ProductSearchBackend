package com.example.dto;

import javax.validation.constraints.NotNull;

public class CategoryDto {
	
	private Long parentId = null;
	@NotNull(message = "category name cannot be null")
	private String name;
	private Long featuredRank=null;
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getFeaturedRank() {
		return featuredRank;
	}
	public void setFeaturedRank(Long featuredRank) {
		this.featuredRank = featuredRank;
	}
	
	
	
}
