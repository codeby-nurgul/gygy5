package com.turkcell.spring_starter.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.turkcell.spring_starter.dto.CreateCategoryRequest;
import com.turkcell.spring_starter.dto.CreatedCategoryResponse;
import com.turkcell.spring_starter.dto.GetCategoryResponse;
import com.turkcell.spring_starter.dto.ListCategoryResponse;
import com.turkcell.spring_starter.dto.UpdateCategoryRequest;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CreatedCategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        // Veritabanında insert-update çalıştır.
        // entity id'e sahipse update
        // entity id'si null ise insert

        Category category = new Category();
        category.setName(createCategoryRequest.getName());

        category = this.categoryRepository.save(category); // ekledikten sonraki halini al

        CreatedCategoryResponse response = new CreatedCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }

    public List<ListCategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();

        // TODO: Refactor
        List<ListCategoryResponse> responseList = new ArrayList<>();

        for (Category category : categories) {
            ListCategoryResponse response = new ListCategoryResponse();
            response.setId(category.getId());
            response.setName(category.getName());
            responseList.add(response);
        }

        return responseList;
    }

    public GetCategoryResponse getById(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow();

        GetCategoryResponse response = new GetCategoryResponse();
        response.setId(category.getId());
        response.setName(category.getName());

        return response;
    }

    public void update(UUID id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(id).orElseThrow();
        category.setName(updateCategoryRequest.getName());
        categoryRepository.save(category);
    }

    public void delete(UUID id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        categoryRepository.delete(category);
    }
}