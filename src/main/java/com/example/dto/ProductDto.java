package com.example.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class ProductDto {

	@NotNull(message = "merchant id cannot be null")
	private Long merchantId;

	private List<FeedDto> feedsDto;
	@NotNull(message = "name cannot be null")
	private String name;
	private String description;

	private String color = null;
	private String size = null;
	
	
	private Long brandId = null;
	
	
	private Long categoryId=null;
	
	private String imageUrl=null;
	
	private int rank;

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public List<FeedDto> getFeedsDto() {
		return feedsDto;
	}

	public void setFeedsDto(List<FeedDto> feedsDto) {
		this.feedsDto = feedsDto;
	}

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	
}
