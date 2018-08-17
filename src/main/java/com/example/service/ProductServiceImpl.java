package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assembler.ProductAssembler;
import com.example.dto.ProductDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Brand;
import com.example.model.Category;
import com.example.model.Merchant;
import com.example.model.Product;
import com.example.repository.IBrandRepository;
import com.example.repository.ICategoryRepository;
import com.example.repository.IMerchantRepository;
import com.example.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {
	@Autowired
	private ProductAssembler productAssembler;

	@Autowired
	private IProductRepository productRepository;
	
	@Autowired 
	private IBrandRepository brandRepository;
	
	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private IMerchantRepository merchantRepository;

	@Override
	public List<Product> viewAll() {
		List<Product> product = productRepository.findAll();
		return product;
	}

	@Override
	public Product viewOne(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product_id", id));
		return product;
	}

	@Override
	public Product update(ProductDto productDto,Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "product ID", id));
		product.setCreatedAt(new Date());
		Merchant merchant = merchantRepository.getOne(productDto.getMerchantId());
		product.setMerchant(merchant);
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setColor(productDto.getColor());
		product.setSize(productDto.getSize());
		Brand brand = brandRepository.getOne(productDto.getBrandId());
		product.setBrand(brand);
		Category category = categoryRepository.getOne(productDto.getCategoryId());
		product.setCategory(category);
		product.setImageUrl(productDto.getImageUrl());
		product.setRank(productDto.getRank());
		productRepository.save(product);
		return product;
	}

	@Override
	public Product create(ProductDto productDto) {
		Product product = productAssembler.createProductEntity(productDto);
		product.setCreatedAt(new java.util.Date());
		productRepository.save(product);
		return product;
	}

	@Override
	public void delete(List<Product> product) {
		//declared in controller
	}

	@Override
	public Product findByName(String productName) {
		Product product = productRepository.findByName(productName);
		return product;
	}

	@Override
	public List<Product> findByMerchantId(Long merchantId) {
		List<Product> product = productRepository.findByMerchantId(merchantId);
		return product;
	}
	
	

}
