package com.example.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.assembler.StoreAssembler;
import com.example.dto.StoreDto;
import com.example.exception.ResourceNotFoundException;
import com.example.model.Merchant;
import com.example.model.Store;
import com.example.repository.IMerchantRepository;
import com.example.repository.IStoreRepository;

@Service
public class StoreServiceImpl implements IStoreService {
	@Autowired
	private StoreAssembler storeAssembler;

	@Autowired
	private IStoreRepository storeRepository;
	
	@Autowired 
	private IMerchantRepository merchantRepository;

	@Override
	public Store create(StoreDto storeDto) {
		Store store = storeAssembler.createStoreEntity(storeDto);
		store.setCreatedAt(new Date());
		storeRepository.save(store);
		return store;
	}

	@Override
	public List<Store> viewAll() {

		return storeRepository.findAll();
	}

	@Override
	public Store viewOne(Long id) {

		return storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Store", "Store_id", id));
	}

	@Override
	public Store update(StoreDto storeDto,Long id) {
		Store store = storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("store", "id", id));
		store.setCreatedAt(new Date());
		store.setAddress(storeDto.getAddress());
		store.setDescription(storeDto.getDescription());
		store.setLatitude(storeDto.getLatitude());
		store.setLongitude(storeDto.getLongitude());
		Merchant merchant = merchantRepository.getOne(storeDto.getMerchantId());
		store.setMerchant(merchant);
		store.setName(storeDto.getName());
		store.setOpeningHours(storeDto.getOpeningHours());
		store.setPaymentMethods(storeDto.getPaymentMethodId());
		store.setPhone(storeDto.getPhone());
		store.setPostalCode(storeDto.getPostalCode());
		storeRepository.save(store);
		return store;
		
	}
	@Override
	public void delete(List<Store> storeList) {

	}

	@Override
	public List<Store> findByName(String name) {
		List<Store> store = storeRepository.findByName(name);
		return store;
	}

}
