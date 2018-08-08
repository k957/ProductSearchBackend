package com.example.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.dto.ProductDto;
import com.example.model.Brand;
import com.example.model.Merchant;
import com.example.model.Product;
import com.example.repository.IBrandRepository;
import com.example.repository.IMerchantRepository;

@Component
public class ProductAssembler {
	@Autowired
	private IMerchantRepository merchantRepository;
	
	@Autowired 
	private IBrandRepository brandRepository;
	
	public List<Product> createProductEntity(List<ProductDto> productDtoList) {
		
		List<Product> productList = new ArrayList<>();
		productDtoList.forEach(productDto -> {
			Product product = new Product();
			Merchant merchant = merchantRepository.getOne(productDto.getMerchantId());
			product.setMerchant(merchant);
			Brand brand = brandRepository.getOne(productDto.getBrandId());
			product.setBrand(brand);
			product.setName(productDto.getName());
			product.setColor(productDto.getColor());
			product.setSize(productDto.getSize());
			product.setDescription(productDto.getDescription());
			product.setImageUrl(productDto.getImageUrl());
			product.setRank(productDto.getRank());
			productList.add(product);
		});

		return productList;
	}
	
	public Product createProductEntity(ProductDto productDto) {
		Product product = new Product();
		Merchant merchant = merchantRepository.getOne(productDto.getMerchantId());
		product.setMerchant(merchant);
		Brand brand = brandRepository.getOne(productDto.getBrandId());
		product.setBrand(brand);
		product.setName(productDto.getName());
		product.setColor(productDto.getColor());
		product.setSize(productDto.getSize());
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setRank(productDto.getRank());
		return product;
	}
}
