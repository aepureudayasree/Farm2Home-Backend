package com.farm2home.farm2home.repository;

import com.farm2home.farm2home.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}