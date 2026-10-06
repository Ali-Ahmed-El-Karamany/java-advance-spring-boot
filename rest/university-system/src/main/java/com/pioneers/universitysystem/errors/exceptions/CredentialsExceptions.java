package com.pioneers.universitysystem.errors.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@Getter
public class CredentialsExceptions extends RuntimeException {

    public static final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    public static final String ERROR = "credentialException";

    private final Timestamp timestamp = Timestamp.from(Instant.now());


    public CredentialsExceptions(String message, String logMessage) {
        super(message);

        log.error(logMessage);
    }
}
