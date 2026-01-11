package com.example.invoTech.tenantdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoTech.tenantdb.entity.Product;
import com.example.invoTech.tenantdb.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@GetMapping
	public List<Product> getAll() {
		return service.getAll();
	}

	@PostMapping
	public Product create(@RequestBody Product product) {
		return service.save(product);
	}

	@GetMapping("/{id}")
	public Product get(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public Product update(@PathVariable Long id, @RequestBody Product product) {
		product.setId(id);
		return service.save(product);
	}

	@DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
