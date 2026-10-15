package com.pioneers.universitysystem.errors.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

import static com.pioneers.universitysystem.utils.TimeHelper.currentTimeStamp;

@Slf4j
@Getter
public class PasswordMismatchException extends RuntimeException {

    public static final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    public static final String ERROR = "passwordMismatchException";

    private final Timestamp timestamp = currentTimeStamp();

    public PasswordMismatchException(String message, String logMessage) {
        super(message);

        log.error(logMessage);
    }
}
