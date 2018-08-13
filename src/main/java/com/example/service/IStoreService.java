package com.example.service;

import java.util.List;

import com.example.dto.StoreDto;
import com.example.model.Store;

public interface IStoreService {
	Store create(StoreDto storeDto);

	List<Store> viewAll();

	Store viewOne(Long id);

	Store update(StoreDto storeDto,Long id);

	void delete(List<Store> storeList);
	
	List<Store> findByName(String name);
}
