package com.pioneers.universitysystem.models.dtos.requests;

import java.util.Objects;

public class StudentUpdateRequest {
    private final String firstName ;
    private final String lastName ;
    private final int age ;
    private final String email;
    private final String password;
    private final int score;


    public StudentUpdateRequest(String firstName, String lastName, int age, String email, String password, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StudentUpdateRequest that = (StudentUpdateRequest) o;
        return age == that.age && score == that.score && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, email, password, score);
    }

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
