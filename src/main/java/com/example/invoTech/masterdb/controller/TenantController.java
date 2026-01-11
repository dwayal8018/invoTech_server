package com.example.invoTech.masterdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoTech.masterdb.entity.Tenant;
import com.example.invoTech.masterdb.service.TenantProvisionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class TenantController {
	@Autowired
	TenantProvisionService tenantProvisionService;

	@PostMapping("/register")
	public ResponseEntity<Tenant> register(@RequestBody Tenant tenant) {
		return ResponseEntity.ok(tenantProvisionService.registerTenant(tenant));
	}
}
