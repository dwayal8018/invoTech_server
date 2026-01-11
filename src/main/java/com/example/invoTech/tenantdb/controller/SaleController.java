package com.example.invoTech.tenantdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoTech.tenantdb.entity.Sale;
import com.example.invoTech.tenantdb.service.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @PostMapping
    public Sale create(@RequestBody Sale sale) {
        return service.save(sale);
    }

    @GetMapping
    public List<Sale> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Sale get(@PathVariable Long id) {
        return service.getById(id);
    }
}
