package com.pioneers.universitysystem.errors.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutException extends RuntimeException {
    public LogoutException(String message, String logMessage) {
        super(message);

        log.error(logMessage);
    }
}
