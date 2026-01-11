package com.example.invoTech.tenantdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.invoTech.tenantdb.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
