package com.turkcell.library_application.dto;

public class CreatedStaffResponse {
    private int id;
    private String fullName;

    public CreatedStaffResponse() {
    }

    public CreatedStaffResponse(int id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
