package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.entities.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.pioneers.universitysystem.repositories.StudentRepository.*;
import static com.pioneers.universitysystem.utils.NameBuilderUtils.buildFullName;
import static com.pioneers.universitysystem.utils.PasswordUtils.hashPassword;
import static com.pioneers.universitysystem.utils.validators.StudentValidator.validateRegisterRequest;

@RestController
@RequestMapping("student")
public class StudentController {

    @PostMapping("signUp")
    public ResponseEntity<List<String>> signup(@RequestBody final StudentRegisterRequest registerRequest) {

        final List<String> errors = validateRegisterRequest(registerRequest);

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Optional<Student> optionalStudent = findByEmail(registerRequest.getEmail());
        if (optionalStudent.isPresent()) {
            return ResponseEntity.badRequest().body(List.of("Student is already registered"));
        }

        final String fullName = buildFullName(registerRequest.getFirstName(), registerRequest.getLastName());

        final String hashedPassword;
        try {
            hashedPassword = hashPassword(registerRequest.getPassword());
        } catch (CredentialsExceptions e) {
            return ResponseEntity.badRequest().build();
        }

        final Student student =
                new Student(UUID.randomUUID(), fullName, registerRequest.getAge(),
                        registerRequest.getEmail(), hashedPassword, false, 0.0F, 0.0F);

        save(student);
        return ResponseEntity.ok(List.of("Successfully registered student with email: " + registerRequest.getEmail()));
    }
}
