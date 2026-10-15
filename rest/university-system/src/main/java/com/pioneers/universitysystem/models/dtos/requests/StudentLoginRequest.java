package com.pioneers.universitysystem.models.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record StudentLoginRequest(
        @Email(message = "{validation.email.pattern}" )
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9\\s])\\S{9,31}$",
                message = "{validation.password.pattern}")
        String password
) {

    @Override
    public String toString() {
        return "StudentLoginRequest{" +
                "email='" + email + '\'' +
                ", password=********" +
                '}';
    }
}
