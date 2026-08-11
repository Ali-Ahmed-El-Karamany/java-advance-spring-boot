package com.pioneers.universitysystem.models.dtos.responses;

import java.util.Objects;

public class StudentResponse {
    private final String fullName;
    private final int age;
    private final String email;

    public StudentResponse(String fullName, int age, String email) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentResponse that = (StudentResponse) o;
        return age == that.age && Objects.equals(fullName, that.fullName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, age, email);
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
