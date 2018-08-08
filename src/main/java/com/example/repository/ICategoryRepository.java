package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Category;

public interface ICategoryRepository extends JpaRepository<Category, Long>{
	List<Category> findByName(String name);
}