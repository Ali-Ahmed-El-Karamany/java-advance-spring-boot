package com.pioneers.universitysystem.errors.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
public class ValidationException extends RuntimeException {

    List<String> errors;

    public ValidationException(String message, String logMessage, List<String> errors) {
        super(message);

        this.errors = errors;
        log.error(logMessage);
    }
}
