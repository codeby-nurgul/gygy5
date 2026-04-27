package com.turkcell.library_application.dto;

import java.util.Set;

public class ListBookResponse {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private Set<String> categoryNames;

    public ListBookResponse() {
    }

    public ListBookResponse(int id, String isbn, String title, String author, Set<String> categoryNames) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.categoryNames = categoryNames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(Set<String> categoryNames) {
        this.categoryNames = categoryNames;
    }
}
