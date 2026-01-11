package com.example.invoTech.masterdb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.invoTech.masterdb.entity.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	 Optional<Tenant> findByUsername(String username);
}
