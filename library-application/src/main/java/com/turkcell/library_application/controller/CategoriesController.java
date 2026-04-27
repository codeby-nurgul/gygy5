package com.turkcell.library_application.controller;

import com.turkcell.library_application.dto.CreateCategoryRequest;
import com.turkcell.library_application.dto.CreatedCategoryResponse;
import com.turkcell.library_application.dto.ListCategoryResponse;
import com.turkcell.library_application.dto.UpdateCategoryRequest;
import com.turkcell.library_application.service.CategoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    private final CategoryServiceImpl categoryService;

    public CategoriesController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public CreatedCategoryResponse create(@RequestBody CreateCategoryRequest request) {
        return categoryService.create(request);
    }

    @GetMapping
    public List<ListCategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        categoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        categoryService.delete(id);
    }
}
