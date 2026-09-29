package com.pioneers.universitysystem.errors.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;

@Slf4j
@Getter
public class ValidationException extends RuntimeException {

    public static final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    public static final String ERROR = "ValidationException";

    private final Timestamp timestamp = Timestamp.from(Instant.now());

    private final Map<String, String> errors;

    public ValidationException(String message, String logMessage, Map<String, String> errors) {
        super(message);

        this.errors = errors;

        log.error(logMessage);
    }
}
