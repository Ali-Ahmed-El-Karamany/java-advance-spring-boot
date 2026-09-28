package com.pioneers.universitysystem.repositories;

import com.pioneers.universitysystem.models.entities.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {

    void save(Student student);

    void update(UUID id, Student student);

    void delete(UUID id);

    void deleteAll();

    Optional<Student> findByEmail(String email);

    Optional<Student> findById(UUID id);

    List<Student> findAllSortedByAge();

    List<Student> findAllSucceeded();
}
