package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private Long parentId;
	private String name;
	private Long featuredRank;
	private Date createdAt;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
	private List<Product> products;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Category(Long id, Long parentId, String name, Long featuredRank, Date createdAt, List<Product> products) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.featuredRank = featuredRank;
		this.createdAt = createdAt;
		this.products = products;
	}
	public Category() {
		super();
		
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", parentId=" + parentId + ", name=" + name + ", featuredRank=" + featuredRank
				+ ", createdAt=" + createdAt + ", products=" + products + "]";
	}
	
	
}
