package com.example.invoTech.tenant;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver<String> {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String tenantId = (String) TenantContext.getCurrentTenant();
        return tenantId != null ? tenantId : "default";
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
