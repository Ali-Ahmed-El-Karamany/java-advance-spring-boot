package com.pioneers.universitysystem.models.dtos.requests;

public record StudentUpdateRequest(String firstName, String lastName, int age, String email, String password,
                                   int score) {

    @Override
    public String toString() {
        return "StudentUpdateRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password=********" +
                ", score=" + score +
                '}';
    }
}
