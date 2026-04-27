package com.turkcell.spring_starter.dto;

import java.util.UUID;
import java.util.Set;

public class CreateProductRequest {
    private String name;
    private String description;
    private UUID categoryId;
    private Set<UUID> tagIds;

    public CreateProductRequest() {
    }

    public CreateProductRequest(String name, String description, UUID categoryId, Set<UUID> tagIds) {
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.tagIds = tagIds;
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

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public Set<UUID> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<UUID> tagIds) {
        this.tagIds = tagIds;
    }
}
