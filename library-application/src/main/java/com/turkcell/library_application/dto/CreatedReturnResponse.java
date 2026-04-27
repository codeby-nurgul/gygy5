package com.turkcell.library_application.dto;

public class CreatedReturnResponse {
    private int id;
    private String bookTitle;
    private String status;

    public CreatedReturnResponse() {
    }

    public CreatedReturnResponse(int id, String bookTitle, String status) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
