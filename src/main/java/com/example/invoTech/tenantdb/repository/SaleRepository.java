package com.example.invoTech.tenantdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoTech.tenantdb.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
