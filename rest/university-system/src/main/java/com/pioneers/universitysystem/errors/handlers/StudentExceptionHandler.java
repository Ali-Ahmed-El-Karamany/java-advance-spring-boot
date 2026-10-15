package com.pioneers.universitysystem.errors.handlers;

import com.pioneers.universitysystem.errors.exceptions.StudentException;
import com.pioneers.universitysystem.errors.models.ErrorDescription;
import com.pioneers.universitysystem.errors.models.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentException.class)
    public ErrorResponse<ErrorDescription> handleStudentException(final StudentException e) {
        return new ErrorResponse<>(StudentException.STATUS_CODE, e.getTimestamp(),
                new ErrorDescription(StudentException.ERROR, e.getMessage()));
    }
}
