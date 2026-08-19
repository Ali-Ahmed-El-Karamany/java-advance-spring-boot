package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;
import com.pioneers.universitysystem.models.dtos.requests.StudentUpdateRequest;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;
import com.pioneers.universitysystem.utils.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.pioneers.universitysystem.repositories.StudentRepository.*;
import static com.pioneers.universitysystem.utils.Mappers.toStudentResponse;
import static com.pioneers.universitysystem.utils.NameBuilderUtils.buildFullName;
import static com.pioneers.universitysystem.utils.PasswordUtils.hashPassword;

@RestController
@RequestMapping("student")
public class StudentController {
    @GetMapping("findAll")
    public ResponseEntity<?> findAllStudentsApi() {
        final Collection<Student> allStudents = findAll();

        if (allStudents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No registered students");
        }

        final List<StudentResponse> studentResponses = allStudents.stream()
                .map(Mappers::toStudentResponse).toList();

        return ResponseEntity.ok(studentResponses);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findByIdApi(@PathVariable("id") UUID id) {
        final Optional<Student> optionalStudent = findById(id);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        final StudentResponse studentResponse = toStudentResponse(optionalStudent.get());

        return ResponseEntity.ok(studentResponse);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateApi(
            @PathVariable("id") UUID id, @RequestBody StudentUpdateRequest updateRequest
    ) {
        final Optional<Student> optionalStudent = findById(id);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        final String updatedFullName = buildFullName(updateRequest.getFirstName(), updateRequest.getLastName());

        try {
            final String updatedHashedPassword = hashPassword(updateRequest.getPassword());
        } catch (CredentialsExceptions e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }

        final Student foundStudent = optionalStudent.get();

        foundStudent.setFullName(updatedFullName);
        foundStudent.setAge(updateRequest.getAge());
        foundStudent.setEmail(updateRequest.getEmail());
        foundStudent.setPassword(updateRequest.getPassword());
        foundStudent.setScore(updateRequest.getScore());

        return ResponseEntity.ok("Successfully updated student with email: " + foundStudent.getEmail());
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteApi(@PathVariable UUID id) {
        final Optional<Student> optionalStudent = findById(id);
        if (optionalStudent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }

        delete(id);

        return ResponseEntity.ok("Successfully deleted student with email: " + optionalStudent.get().getEmail());
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<String> deleteAllApi() {
        deleteAll();

        return ResponseEntity.ok("Successfully deleted all students");
    }
}
