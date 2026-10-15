package com.pioneers.universitysystem.errors.exceptions;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

import static com.pioneers.universitysystem.utils.TimeHelper.currentTimeStamp;

@Slf4j
@Getter
public class LogoutException extends RuntimeException {

    public static final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    public static final String ERROR = "logoutException";

    private final Timestamp timestamp = currentTimeStamp();

    public LogoutException(String message, String logMessage) {
        super(message);

        log.error(logMessage);
    }
}
