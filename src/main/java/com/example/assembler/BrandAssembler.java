package com.example.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.dto.BrandDto;
import com.example.model.Brand;

@Component
public class BrandAssembler {
	
	
	public List<Brand> createBrandEntity(List<BrandDto> brandDtoList){
		List<Brand> brandList = new ArrayList<>();
		brandDtoList.forEach(brandDto -> {
			Brand brand = new Brand();
			brand.setName(brandDto.getName());
			brand.setDescription(brandDto.getDescription());
			brandList.add(brand);
		});
		return brandList;
	}
	
	public Brand createBrandEntity(BrandDto brandDto){
		Brand brand = new Brand();
		brand.setName(brandDto.getName());
		brand.setDescription(brandDto.getDescription());
		return brand;
	}
}
