package com.example.invoTech.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.example.invoTech.tenant.TenantContext;

public class MultiTenantDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		  // This tells Hibernate which tenant/schema to use
        return TenantContext.getCurrentTenant();
	}

}
