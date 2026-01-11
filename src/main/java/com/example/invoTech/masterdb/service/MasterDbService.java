package com.example.invoTech.masterdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.invoTech.masterdb.entity.Tenant;
import com.example.invoTech.masterdb.repository.TenantRepository;
@Service
public class MasterDbService {
	private final TenantRepository tenantRepository;

	public MasterDbService(TenantRepository tenantRepository) {
		this.tenantRepository = tenantRepository;
	}

	public List<Tenant> getAllTenants() {
		return tenantRepository.findAll();
	}

	public Tenant getTenantByUsername(String username) {
		return tenantRepository.findByUsername(username).orElse(null);
	}
}
