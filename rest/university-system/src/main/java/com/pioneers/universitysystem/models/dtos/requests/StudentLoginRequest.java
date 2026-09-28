package com.pioneers.universitysystem.models.dtos.requests;

public record StudentLoginRequest(String email, String password) {

    @Override
    public String toString() {
        return "StudentLoginRequest{" +
                "email='" + email + '\'' +
                ", password=********" +
                '}';
    }
}
