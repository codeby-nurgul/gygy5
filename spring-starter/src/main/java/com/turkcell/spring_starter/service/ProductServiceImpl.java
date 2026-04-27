package com.turkcell.spring_starter.service;

import com.turkcell.spring_starter.dto.*;
import com.turkcell.spring_starter.entity.Category;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.CategoryRepository;
import com.turkcell.spring_starter.repository.ProductRepository;
import com.turkcell.spring_starter.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public ProductServiceImpl(ProductRepository productRepository, 
                              CategoryRepository categoryRepository,
                              TagRepository tagRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

    public CreatedProductResponse create(CreateProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        product.setCategory(category);

        if (request.getTagIds() != null) {
            List<Tag> tags = tagRepository.findAllById(request.getTagIds());
            product.setTags(new HashSet<>(tags));
        }

        product = productRepository.save(product);
        
        CreatedProductResponse response = new CreatedProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        return response;
    }

    public List<ListProductResponse> getAll() {
        List<Product> products = productRepository.findAll();
        List<ListProductResponse> responseList = new ArrayList<>();

        for (Product product : products) {
            ListProductResponse response = new ListProductResponse();
            response.setId(product.getId());
            response.setName(product.getName());
            response.setCategoryName(product.getCategory().getName());
            responseList.add(response);
        }
        return responseList;
    }

    public GetProductResponse getById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow();
        
        GetProductResponse response = new GetProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setCategoryName(product.getCategory().getName());
        
        if (product.getTags() != null) {
            Set<String> tagNames = product.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.toSet());
            response.setTagNames(tagNames);
        }
        
        return response;
    }

    public void update(UUID id, UpdateProductRequest request) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(request.getName());
        product.setDescription(request.getDescription());

        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow();
        product.setCategory(category);

        if (request.getTagIds() != null) {
            List<Tag> tags = tagRepository.findAllById(request.getTagIds());
            product.setTags(new HashSet<>(tags));
        } else {
            product.setTags(null);
        }

        productRepository.save(product);
    }

    public void delete(UUID id) {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
    }
}
