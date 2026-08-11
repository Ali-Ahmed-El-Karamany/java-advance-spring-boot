package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;
import com.pioneers.universitysystem.models.dtos.requests.StudentLoginRequest;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.entities.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.pioneers.universitysystem.repositories.StudentRepository.*;
import static com.pioneers.universitysystem.utils.NameBuilderUtils.buildFullName;
import static com.pioneers.universitysystem.utils.PasswordUtils.hashPassword;
import static com.pioneers.universitysystem.utils.PasswordUtils.isPasswordMatched;
import static com.pioneers.universitysystem.utils.validators.StudentValidator.validateRegisterRequest;

@RestController
@RequestMapping("student")
public class StudentController {

    @PostMapping("signUp")
    public ResponseEntity<List<String>> signupApi(@RequestBody final StudentRegisterRequest registerRequest) {

        final List<String> errors = validateRegisterRequest(registerRequest);

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(errors);
        }

        final Optional<Student> optionalStudent = findByEmail(registerRequest.getEmail());
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

    @PostMapping("login")
    public ResponseEntity<String> loginApi(@RequestBody StudentLoginRequest loginRequest) {
        final Optional<Student> optionalStudent = findByEmail(loginRequest.getEmail());

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Student with email: " + loginRequest.getEmail() + " not registered");
        }

        final Student foundStudent = optionalStudent.get();
        try {
            if (!isPasswordMatched(loginRequest.getPassword(), foundStudent.getPassword())) {
                return ResponseEntity.badRequest().body("Wrong password");
            }
        } catch (CredentialsExceptions e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }

        if (foundStudent.isLoggedIn()) {
            return ResponseEntity.badRequest().body("Already logged in");
        }

        foundStudent.setLoggedIn(true);

        return ResponseEntity.ok().body("Student with email: " + foundStudent.getEmail() + " logged in successfully!!");
    }

    @PostMapping("logout/{studentEmail}")
    public ResponseEntity<String> logoutApi(@PathVariable("studentEmail") String email) {
        final Optional<Student> optionalStudent = findByEmail(email);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.badRequest().body("Student with email: " + email + " not registered");
        }

        final Student foundStudent = optionalStudent.get();
        if (!foundStudent.isLoggedIn()) {
            return ResponseEntity.badRequest().body("Student already logged out");
        }

        foundStudent.setLoggedIn(false);

        return ResponseEntity.ok().body("Student with email: " + email + " logged out successfully!");
    }

    @PostMapping("saveAll")
    public ResponseEntity<Map<String, List<String>>> registerMultipleStudentsApi(
            @RequestBody List<StudentRegisterRequest> registerRequests
    ) {
        final Map<String, List<String>> unSavedStudents = new HashMap<>();

        for (StudentRegisterRequest registerRequest : registerRequests) {
            final ResponseEntity<List<String>> response = signupApi(registerRequest);

            if (response.getStatusCode() != HttpStatus.OK) {
                unSavedStudents.put(registerRequest.getEmail(), response.getBody());
            }
        }

        return ResponseEntity.ok(unSavedStudents);
    }
}
