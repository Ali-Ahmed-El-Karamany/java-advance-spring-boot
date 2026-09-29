package com.pioneers.universitysystem.errors.models;

import lombok.Builder;

import java.sql.Timestamp;
import java.util.Optional;

@Builder
public record ErrorResponse<T>(
        int status,
        String error,
        String description,
        Timestamp timestamp,
        Optional<T> errors) {
}
