package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Product;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
	
	Product findByName(String productName);
	List<Product> findByMerchantId(Long merchantId);
}
