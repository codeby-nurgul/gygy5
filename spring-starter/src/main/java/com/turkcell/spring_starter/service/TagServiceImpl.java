package com.turkcell.spring_starter.service;

import com.turkcell.spring_starter.dto.*;
import com.turkcell.spring_starter.entity.Product;
import com.turkcell.spring_starter.entity.Tag;
import com.turkcell.spring_starter.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl {
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public CreatedTagResponse create(CreateTagRequest request) {
        Tag tag = new Tag();
        tag.setName(request.getName());
        tag = tagRepository.save(tag);
        
        CreatedTagResponse response = new CreatedTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());
        return response;
    }

    public List<ListTagResponse> getAll() {
        List<Tag> tags = tagRepository.findAll();
        List<ListTagResponse> responseList = new ArrayList<>();

        for (Tag tag : tags) {
            ListTagResponse response = new ListTagResponse();
            response.setId(tag.getId());
            response.setName(tag.getName());
            responseList.add(response);
        }
        return responseList;
    }

    public GetTagResponse getById(UUID id) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        
        GetTagResponse response = new GetTagResponse();
        response.setId(tag.getId());
        response.setName(tag.getName());
        
        if (tag.getProducts() != null) {
            Set<String> productNames = tag.getProducts().stream()
                    .map(Product::getName)
                    .collect(Collectors.toSet());
            response.setProductNames(productNames);
        }
        
        return response;
    }

    public void update(UUID id, UpdateTagRequest request) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        tag.setName(request.getName());
        tagRepository.save(tag);
    }

    public void delete(UUID id) {
        Tag tag = tagRepository.findById(id).orElseThrow();
        tagRepository.delete(tag);
    }
}
