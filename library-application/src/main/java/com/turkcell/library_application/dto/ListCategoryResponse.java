package com.turkcell.library_application.dto;

public class ListCategoryResponse {
    private int id;
    private String name;

    public ListCategoryResponse() {
    }

    public ListCategoryResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
