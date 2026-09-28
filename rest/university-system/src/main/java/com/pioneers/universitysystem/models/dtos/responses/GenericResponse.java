package com.pioneers.universitysystem.models.dtos.responses;

public record GenericResponse<T>(String message, T body) {
}
