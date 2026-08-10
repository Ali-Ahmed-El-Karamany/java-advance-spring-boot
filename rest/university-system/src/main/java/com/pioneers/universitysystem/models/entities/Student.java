package com.pioneers.universitysystem.models.entities;

import java.util.Objects;
import java.util.UUID;

public class Student {
    private UUID id;
    private String fullName;
    private int age;
    private String email;
    private String password;
    private boolean isLoggedIn;
    private float score;
    private float finalScore;

    public Student(UUID id, String fullName, int age, String email, String password,
                   boolean isLoggedIn, float score, float finalScore) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
        this.score = score;
        this.finalScore = finalScore;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age
                && isLoggedIn == student.isLoggedIn
                && Float.compare(score, student.score) == 0
                && Float.compare(finalScore, student.finalScore) == 0
                && Objects.equals(id, student.id)
                && Objects.equals(fullName, student.fullName)
                && Objects.equals(email, student.email)
                && Objects.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, age, email, password, isLoggedIn, score, finalScore);
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
