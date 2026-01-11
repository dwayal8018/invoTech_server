package com.example.invoTech.tenantdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.invoTech.tenantdb.entity.Product;
import com.example.invoTech.tenantdb.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository repo;

	public ProductService(ProductRepository repo) {
		this.repo = repo;
	}

	public Product save(Product product) {
		return repo.save(product);
	}

	public List<Product> getAll() {
		return repo.findAll();
	}

	public Product getById(Long id) {
		return repo.findById(id).orElse(null);
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}
