package com.turkcell.library_application.dto;

import java.time.LocalDate;

public class ListBorrowResponse {
    private int id;
    private String bookTitle;
    private String studentFullName;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private String status;

    public ListBorrowResponse() {
    }

    public ListBorrowResponse(int id, String bookTitle, String studentFullName, LocalDate borrowDate, LocalDate dueDate, String status) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.studentFullName = studentFullName;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
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

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
