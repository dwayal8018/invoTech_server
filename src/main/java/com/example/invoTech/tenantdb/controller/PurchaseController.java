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

import com.example.invoTech.tenantdb.entity.Purchase;
import com.example.invoTech.tenantdb.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService service;

    public PurchaseController(PurchaseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Purchase> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Purchase create(@RequestBody Purchase purchase) {
        return service.save(purchase);
    }

    @GetMapping("/{id}")
    public Purchase get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Purchase update(@PathVariable Long id, @RequestBody Purchase purchase) {
        purchase.setId(id);
        return service.save(purchase);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        // Assuming PurchaseService has a delete method, but it doesn't. Need to add it.
    }
}
