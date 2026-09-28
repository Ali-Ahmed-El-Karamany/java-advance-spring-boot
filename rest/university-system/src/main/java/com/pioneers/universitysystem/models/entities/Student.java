package com.pioneers.universitysystem.models.entities;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Student {
    private UUID id;
    private String fullName;
    private int age;
    private String email;
    private String password;
    private boolean isLoggedIn;
    private float score;
    private float finalScore;

    public boolean isPassedExam() {
        return (score / finalScore) > 0.5F;
    }

    public void login() {
        this.isLoggedIn = true;
    }

    public void logout() {
        this.isLoggedIn = false;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password=********" +
                ", isLoggedIn=" + isLoggedIn +
                ", score=" + score +
                ", finalScore=" + finalScore +
                '}';
    }
}
