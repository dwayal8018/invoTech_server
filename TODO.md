# Multi-Tenant Spring Boot Application Setup Checklist

## Root Cause Analysis
The application fails with "jdbcUrl is required with driverClassName" because:
1. MasterDbConfig creates a primary data source with JPA repositories
2. MultiTenantConfig creates a tenant data source
3. During startup, Spring tries to initialize both data sources
4. The master data source configuration conflicts with tenant data source

## Required Components for Multi-Tenant Application

### 1. Database Setup
- [ ] Master database: `inventory_master` with `tenants` table
- [ ] Tenant databases: Dynamic databases per tenant

### 2. Configuration Classes
- [ ] MasterDbConfig: For master database operations (tenant metadata)
- [ ] MultiTenantConfig: For tenant data source routing
- [ ] TenantDbConfig: For tenant JPA repositories

### 3. Core Components
- [ ] MultiTenantDataSource: Custom data source for routing
- [ ] CurrentTenantIdentifierResolverImpl: Tenant context resolver
- [ ] TenantContext: Thread-local tenant storage
- [ ] TenantFilter: HTTP filter for tenant identification

### 4. Application Properties
- [ ] Master DB connection properties
- [ ] JPA settings for tenant databases
- [ ] Multi-tenancy settings

### 5. Entity Classes
- [ ] Master entities: Tenant
- [ ] Tenant entities: User, Product, Purchase, Sale, etc.

### 6. Repository Classes
- [ ] Master repositories: TenantRepository
- [ ] Tenant repositories: UserRepository, ProductRepository, etc.

### 7. Service Classes
- [ ] Master services: TenantProvisionService
- [ ] Tenant services: AuthService, ProductService, etc.

### 8. Controller Classes
- [ ] Master controllers: TenantController
- [ ] Tenant controllers: AuthController, ProductController, etc.

### 9. Security Configuration
- [ ] SecurityConfig: Authentication/authorization
- [ ] JwtAuthenticationFilter: JWT token processing
- [ ] UserDetailsServiceImpl: User authentication

## Current Issues to Fix

### Issue 1: Conflicting Data Sources
- MasterDbConfig creates primary data source with JPA
- MultiTenantConfig creates tenant data source
- Both try to initialize during startup

### Issue 2: Missing Tenant Database Initialization
- Tenant data sources are not pre-loaded
- No fallback mechanism for unknown tenants

### Issue 3: JPA Configuration Conflicts
- Multiple EntityManagerFactory beans
- Conflicting transaction managers

## Fix Plan

### Step 1: Remove Master JPA Configuration
- Remove @EnableJpaRepositories from MasterDbConfig
- Remove masterEntityManagerFactory and masterTransactionManager
- Use plain JDBC for master database operations

### Step 2: Simplify Multi-Tenant Data Source
- Ensure tenantDataSource is the primary data source
- Pre-load tenant data sources from master DB
- Add proper error handling

### Step 3: Update Application Properties
- Remove master JPA properties
- Configure tenant JPA properties

### Step 4: Test Application Startup
- Verify master DB connection works
- Verify tenant routing works
- Test with sample tenant data
