package com.turkcell.spring_starter.dto;

public class CreateTagRequest {
    private String name;

    public CreateTagRequest() {
    }

    public CreateTagRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
