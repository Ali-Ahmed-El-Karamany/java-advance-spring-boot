package com.pioneers.universitysystem.repositories;

import com.pioneers.universitysystem.models.entities.Student;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StudentRepository {
    public static final Map<UUID, Student> STUDENTS_DB = new ConcurrentHashMap<>();

    public static void save(Student student) {
        STUDENTS_DB.put(student.getId(), student);
    }

    public static Optional<Student> findByEmail(final String email) {
        return STUDENTS_DB.values().stream()
                .filter(student -> student.getEmail().equals(email))
                .findFirst();
    }
}
