package com.example.invoTech.config;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import com.example.invoTech.tenant.TenantContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TenantFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Example: get tenant from header (in prod use JWT)
		String tenantDb = request.getHeader("X-Tenant-Db");
		if (tenantDb != null) {
			TenantContext.setCurrentTenant(tenantDb);
		}

		try {
			filterChain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
	}
}
