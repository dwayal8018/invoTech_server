package com.example.invoTech.tenantdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.invoTech.tenantdb.entity.Borrowing;

public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
}
