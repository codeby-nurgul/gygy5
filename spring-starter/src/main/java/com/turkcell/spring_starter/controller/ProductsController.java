package com.turkcell.spring_starter.controller;

import com.turkcell.spring_starter.dto.*;
import com.turkcell.spring_starter.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductServiceImpl productService;

    public ProductsController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @PostMapping
    public CreatedProductResponse create(@RequestBody CreateProductRequest request) {
        return productService.create(request);
    }

    @GetMapping
    public List<ListProductResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public GetProductResponse getById(@PathVariable UUID id) {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable UUID id, @RequestBody UpdateProductRequest request) {
        productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }
}
