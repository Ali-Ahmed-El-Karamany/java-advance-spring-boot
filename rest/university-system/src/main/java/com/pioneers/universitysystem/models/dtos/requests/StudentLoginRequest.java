package com.pioneers.universitysystem.models.dtos.requests;

import java.util.Objects;

public class StudentLoginRequest {
    private final String email;
    private final String password;

    public StudentLoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentLoginRequest that = (StudentLoginRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "StudentLoginRequest{" +
                "email='" + email + '\'' +
                ", password=********" +
                '}';
    }
}
