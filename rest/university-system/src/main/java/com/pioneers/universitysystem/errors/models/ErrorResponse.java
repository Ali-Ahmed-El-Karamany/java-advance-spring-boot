package com.pioneers.universitysystem.errors.models;

import java.sql.Timestamp;

public record ErrorResponse<T>(int status, Timestamp timestamp, T body) {
}
