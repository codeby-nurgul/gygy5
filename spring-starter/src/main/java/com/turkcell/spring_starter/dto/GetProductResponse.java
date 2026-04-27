package com.turkcell.spring_starter.dto;

import java.util.UUID;
import java.util.Set;

public class GetProductResponse {
    private UUID id;
    private String name;
    private String description;
    private String categoryName;
    private Set<String> tagNames;

    public GetProductResponse() {
    }

    public GetProductResponse(UUID id, String name, String description, String categoryName, Set<String> tagNames) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryName = categoryName;
        this.tagNames = tagNames;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Set<String> getTagNames() {
        return tagNames;
    }

    public void setTagNames(Set<String> tagNames) {
        this.tagNames = tagNames;
    }
}
