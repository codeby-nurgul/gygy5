package com.turkcell.library_application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ListReturnResponse {
    private int id;
    private String bookTitle;
    private String studentName;
    private LocalDate returnDate;
    private BigDecimal lateFee;

    public ListReturnResponse() {
    }

    public ListReturnResponse(int id, String bookTitle, String studentName, LocalDate returnDate, BigDecimal lateFee) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.studentName = studentName;
        this.returnDate = returnDate;
        this.lateFee = lateFee;
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

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }
}
