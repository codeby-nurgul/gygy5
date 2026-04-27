package com.turkcell.spring_starter.dto;

import java.util.UUID;

public class ListProductResponse {
    private UUID id;
    private String name;
    private String categoryName;

    public ListProductResponse() {
    }

    public ListProductResponse(UUID id, String name, String categoryName) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
