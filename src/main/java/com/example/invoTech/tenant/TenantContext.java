package com.example.invoTech.tenant;

import jakarta.annotation.Nullable;

public class TenantContext {

	private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

	public static @Nullable Object getCurrentTenant() {
		// TODO Auto-generated method stub
		return currentTenant.get();
	}

	public static void setCurrentTenant(String tenant) {
		currentTenant.set(tenant);
	}

	public static void clear() {
		currentTenant.remove();
	}
}
