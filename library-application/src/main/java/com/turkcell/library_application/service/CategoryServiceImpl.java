package com.turkcell.library_application.service;

import com.turkcell.library_application.dto.CreateCategoryRequest;
import com.turkcell.library_application.dto.CreatedCategoryResponse;
import com.turkcell.library_application.dto.ListCategoryResponse;
import com.turkcell.library_application.dto.UpdateCategoryRequest;
import com.turkcell.library_application.entity.Category;
import com.turkcell.library_application.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        category = categoryRepository.save(category);

        return new CreatedCategoryResponse(category.getId(), category.getName());
    }

    public List<ListCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        List<ListCategoryResponse> responseList = new ArrayList<>();
        for (Category category : categories) {
            responseList.add(new ListCategoryResponse(category.getId(), category.getName()));
        }
        return responseList;
    }

    public void update(int id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        categoryRepository.save(category);
    }

    public void delete(int id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
    }
}
