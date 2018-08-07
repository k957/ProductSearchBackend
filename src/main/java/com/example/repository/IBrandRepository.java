package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Brand;

@Repository
public interface IBrandRepository extends JpaRepository<Brand, Long>{

}
