package com.pioneers.universitysystem.errors.handlers;

import com.pioneers.universitysystem.errors.exceptions.StudentException;
import com.pioneers.universitysystem.errors.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class StudentExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(StudentException.class)
    public ErrorResponse<?> handleStudentException(final StudentException e) {
        return ErrorResponse.builder()
                .status(StudentException.STATUS_CODE)
                .error(StudentException.ERROR)
                .description(e.getMessage())
                .timestamp(e.getTimestamp())
                .errors(Optional.empty())
                .build();
    }
}
