package com.turkcell.library_application.exception.details;

public class ValidationDetail {
    private String argument;
    private String message;

    public ValidationDetail() {
    }

    public ValidationDetail(String argument, String message) {
        this.argument = argument;
        this.message = message;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
