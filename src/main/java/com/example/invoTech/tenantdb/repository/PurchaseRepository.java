package com.example.invoTech.tenantdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoTech.tenantdb.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
