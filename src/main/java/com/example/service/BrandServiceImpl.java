package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assembler.BrandAssembler;
import com.example.dto.BrandDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Brand;
import com.example.repository.IBrandRepository;

@Service
public class BrandServiceImpl implements IBrandService{
	
	@Autowired
	private IBrandRepository brandRepository;
	
	@Autowired
	private BrandAssembler brandAssembler;

	@Override
	public Brand create(BrandDto brandDto) {
		Brand brand = brandAssembler.createBrandEntity(brandDto);
		brand.setCreatedAt(new Date());
		brandRepository.save(brand);
		return brand;
	}

	@Override
	public List<Brand> viewAll() {
		List<Brand> brands = brandRepository.findAll();
		return brands;
	}

	@Override
	public Brand viewOne(Long id) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Brand", "brand ID", id));
		return brand;
	}


	@Override
	public void delete(List<Brand> brandList) {
	
	}

}
