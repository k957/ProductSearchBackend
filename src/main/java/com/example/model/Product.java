package com.example.model;

import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "merchant_id")
	private Merchant merchant;

	@OneToMany(mappedBy = "product")
	private List<Feed> feeds;

	private String name;
	private String description;
	private String color;
	private String size;
	@Column(name="created_at")
	private Date createdAt;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}

	public Product() {
		super();

	}
	@JsonIgnore
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product(Long id, Merchant merchant, List<Feed> feeds, String name, String description, String color,
			String size, Date createdAt, Brand brand, Category category) {
		super();
		this.id = id;
		this.merchant = merchant;
		this.feeds = feeds;
		this.name = name;
		this.description = description;
		this.color = color;
		this.size = size;
		this.createdAt = createdAt;
		this.brand = brand;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", merchant=" + merchant + ", feeds=" + feeds + ", name=" + name + ", description="
				+ description + ", color=" + color + ", size=" + size + ", createdAt=" + createdAt + ", brand=" + brand
				+ ", category=" + category + "]";
	}

	

	
}
