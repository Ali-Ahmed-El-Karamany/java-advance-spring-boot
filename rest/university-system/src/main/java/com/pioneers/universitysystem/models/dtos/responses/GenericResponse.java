package com.pioneers.universitysystem.models.dtos.responses;

import java.util.List;
import java.util.Objects;

public class GenericResponse<T> {
    private final String message;
    private final T body;

    public GenericResponse(String message, T body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GenericResponse<?> that = (GenericResponse<?>) o;
        return Objects.equals(message, that.message) && Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, body);
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}
