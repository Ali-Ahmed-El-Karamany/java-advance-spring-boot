package com.pioneers.universitysystem.utils;

import com.pioneers.universitysystem.models.entities.Student;

import java.util.UUID;

public final class StudentBuilder {

    private StudentBuilder() {
        throw new AssertionError("Utility Class");
    }

    public static Student buildRegisteredStudent(String fullName, int age, String email, String hashedPassword) {

        return Student.builder()
                .id(UUID.randomUUID())
                .fullName(fullName)
                .age(age)
                .email(email)
                .password(hashedPassword)
                .build();

    }
}
