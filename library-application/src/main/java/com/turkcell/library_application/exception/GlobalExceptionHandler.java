package com.turkcell.library_application.exception;

import com.turkcell.library_application.exception.details.ErrorResponse;
import com.turkcell.library_application.exception.details.ValidationDetail;
import com.turkcell.library_application.exception.details.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ BusinessException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleBusinessException(BusinessException exception) {
        return new ErrorResponse("Business Rule Violation", "BUSINESS_ERROR", exception.getMessage());
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationDetail> errors = new ArrayList<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            errors.add(new ValidationDetail(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return new ValidationErrorResponse("Validation Error", "VALIDATION_ERROR", "Geçersiz veri girişi", errors);
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception exception) {
        return new ErrorResponse("Internal Server Error", "SERVER_ERROR", "Bir hata oluştu.");
    }
}
