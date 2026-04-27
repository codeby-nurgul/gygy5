package com.turkcell.spring_starter.dto;

import java.util.UUID;
import java.util.Set;

public class GetTagResponse {
    private UUID id;
    private String name;
    private Set<String> productNames;

    public GetTagResponse() {
    }

    public GetTagResponse(UUID id, String name, Set<String> productNames) {
        this.id = id;
        this.name = name;
        this.productNames = productNames;
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

    public Set<String> getProductNames() {
        return productNames;
    }

    public void setProductNames(Set<String> productNames) {
        this.productNames = productNames;
    }
}
