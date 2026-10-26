package com.pioneers.universitysystem.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "students")
public class Student {
    @Id
    @Column(length = 36, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", length = 32, nullable = false)
    private String fullName;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_login", nullable = false)
    private boolean isLoggedIn;

    @Column
    private float score;

    @Column(name = "final_score")
    private float finalScore;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "is_archived")
    private boolean isArchived;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Address address;

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
                ", createdAt=" + createdAt +
                ", isArchived=" + isArchived +
                '}';
    }

    private Student(final StudentBuilder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.age = builder.age;
        this.email = builder.email;
        this.password = builder.password;
        this.isLoggedIn = builder.isLoggedIn;
        this.score = builder.score;
        this.finalScore = builder.finalScore;
        this.createdAt = builder.createdAt;
        this.isArchived = builder.isArchived;
        this.address = builder.address;
    }

    public static StudentBuilder builder() {
        return new StudentBuilder();
    }

    public static class StudentBuilder {
        private UUID id;
        private String fullName;
        private int age;
        private String email;
        private String password;
        private boolean isLoggedIn;
        private float score;
        private float finalScore;
        private Timestamp createdAt;
        private boolean isArchived;
        private Address address;

        public StudentBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        public StudentBuilder fullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }

        public StudentBuilder age(final int age) {
            this.age = age;
            return this;
        }

        public StudentBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public StudentBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public StudentBuilder isLoggedIn(final boolean isLoggedIn) {
            this.isLoggedIn = isLoggedIn;
            return this;
        }

        public StudentBuilder score(final float score) {
            this.score = score;
            return this;
        }

        public StudentBuilder finalScore(final float finalScore) {
            this.finalScore = finalScore;
            return this;
        }

        public StudentBuilder createdAt(final Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public StudentBuilder isArchived(final boolean isArchived) {
            this.isArchived = isArchived;
            return this;
        }

        public StudentBuilder address(final Address address) {
            this.address = address;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
