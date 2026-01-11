package com.example.invoTech.tenantdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoTech.tenantdb.entity.InventoryTransaction;

public interface InventoryTransactionRepository extends JpaRepository<InventoryTransaction, Long> {
}
