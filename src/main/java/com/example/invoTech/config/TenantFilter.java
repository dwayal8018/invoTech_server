package com.example.invoTech.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.invoTech.tenant.TenantContext;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public TenantFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			try {
				String tenantId = jwtUtil.extractTenantId(token);
				if (tenantId != null && !jwtUtil.isTokenExpired(token)) {
					TenantContext.setCurrentTenant(tenantId);
				}
			} catch (Exception e) {
				// Invalid token, continue without setting tenant
			}
		}

		try {
			filterChain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
	}
}
