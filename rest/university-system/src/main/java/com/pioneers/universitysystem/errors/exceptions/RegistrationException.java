package com.pioneers.universitysystem.errors.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RegistrationException extends RuntimeException {
    public RegistrationException(String message, String LogMessage) {
        super(message);

        log.error(LogMessage);
    }
}
