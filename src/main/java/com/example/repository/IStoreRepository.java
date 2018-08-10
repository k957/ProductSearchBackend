package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Store;

@Repository
public interface IStoreRepository extends JpaRepository<Store, Long> {
	
	 List<Store> findByName(String name);
}
