package com.pioneers.universitysystem.models.dtos.requests;

public record StudentRegisterRequest(String firstName, String lastName, int age, String email, String password) {

}
