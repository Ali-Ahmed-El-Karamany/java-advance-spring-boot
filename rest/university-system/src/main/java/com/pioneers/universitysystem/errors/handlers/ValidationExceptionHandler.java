package com.pioneers.universitysystem.errors.handlers;

import com.pioneers.universitysystem.errors.exceptions.ValidationException;
import com.pioneers.universitysystem.errors.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponse<Map<String, String>> handleValidationException(final ValidationException e) {
        return ErrorResponse.<Map<String, String>>builder()
                .status(ValidationException.STATUS_CODE)
                .error(ValidationException.ERROR)
                .description(e.getMessage())
                .timestamp(e.getTimestamp())
                .errors(Optional.of(e.getErrors()))
                .build();
    }
}
