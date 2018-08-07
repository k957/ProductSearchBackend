package com.example.service;

import java.util.List;

import com.example.dto.BrandDto;
import com.example.model.Brand;



public interface IBrandService {
	Brand create(BrandDto brandDto);

	List<Brand> viewAll();

	Brand viewOne(Long id);

	void delete(List<Brand> brandList);
}
