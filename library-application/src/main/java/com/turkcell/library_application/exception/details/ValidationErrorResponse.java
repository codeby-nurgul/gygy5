package com.turkcell.library_application.exception.details;

import java.util.List;

public class ValidationErrorResponse extends ErrorResponse {
    private List<ValidationDetail> errors;

    public ValidationErrorResponse() {
    }

    public ValidationErrorResponse(String title, String type, String message, List<ValidationDetail> errors) {
        super(title, type, message);
        this.errors = errors;
    }

    public List<ValidationDetail> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationDetail> errors) {
        this.errors = errors;
    }
}
