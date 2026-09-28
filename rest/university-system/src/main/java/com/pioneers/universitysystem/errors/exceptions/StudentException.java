package com.pioneers.universitysystem.errors.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentException extends RuntimeException {
    public StudentException(String message, String LogMessage) {
        super(message);

        log.error(LogMessage);
    }
}
