package com.turkcell.library_application.dto;

import java.time.LocalDate;

public class CreatedBorrowResponse {
    private int id;
    private String bookTitle;
    private String studentName;
    private LocalDate dueDate;

    public CreatedBorrowResponse() {
    }

    public CreatedBorrowResponse(int id, String bookTitle, String studentName, LocalDate dueDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.studentName = studentName;
        this.dueDate = dueDate;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
