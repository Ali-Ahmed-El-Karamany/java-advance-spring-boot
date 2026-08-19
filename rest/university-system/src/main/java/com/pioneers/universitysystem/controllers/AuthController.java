package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;
import com.pioneers.universitysystem.models.dtos.requests.StudentLoginRequest;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.GenericResponse;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;
import com.pioneers.universitysystem.utils.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.pioneers.universitysystem.repositories.StudentRepository.findByEmail;
import static com.pioneers.universitysystem.repositories.StudentRepository.save;
import static com.pioneers.universitysystem.utils.NameBuilderUtils.buildFullName;
import static com.pioneers.universitysystem.utils.PasswordUtils.hashPassword;
import static com.pioneers.universitysystem.utils.PasswordUtils.isPasswordMatched;
import static com.pioneers.universitysystem.utils.validators.StudentValidator.validateRegisterRequest;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("signUp")
    public ResponseEntity<GenericResponse<List<String>>> signupApi(
            @RequestBody final StudentRegisterRequest registerRequest) {

        final List<String> errors = validateRegisterRequest(registerRequest);

        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(new GenericResponse<>("Registration failed", errors));
        }

        final Optional<Student> optionalStudent = findByEmail(registerRequest.getEmail());
        if (optionalStudent.isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>("Student is already registered", List.of(registerRequest.getEmail())));
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
        return ResponseEntity.ok(
                new GenericResponse<>("Student Registered Successfully", List.of(registerRequest.getEmail())));
    }

    @PostMapping("login")
    public ResponseEntity<GenericResponse<String>> loginApi(@RequestBody StudentLoginRequest loginRequest) {
        final Optional<Student> optionalStudent = findByEmail(loginRequest.getEmail());

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>("Student not registered", loginRequest.getEmail()));
        }

        final Student foundStudent = optionalStudent.get();
        try {
            if (!isPasswordMatched(loginRequest.getPassword(), foundStudent.getPassword())) {
                return ResponseEntity.badRequest()
                        .body(new GenericResponse<>("Wrong password",  loginRequest.getEmail()));
            }
        } catch (CredentialsExceptions e) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>("Something went wrong",  loginRequest.getEmail()));
        }

        if (foundStudent.isLoggedIn()) {
            return ResponseEntity.badRequest()
                    .body(new GenericResponse<>("Already logged in", loginRequest.getEmail()));
        }

        foundStudent.setLoggedIn(true);

        return ResponseEntity.ok()
                .body(new GenericResponse<>("Student logged in successfully", loginRequest.getEmail()));
    }

    @PostMapping("logout/{studentEmail}")
    public ResponseEntity<GenericResponse<String>> logoutApi(@PathVariable("studentEmail") String email) {
        final Optional<Student> optionalStudent = findByEmail(email);

        if (optionalStudent.isEmpty()) {
            return ResponseEntity.badRequest().body(new GenericResponse<String>(
                    "Student not registered: ", email));
        }

        final Student foundStudent = optionalStudent.get();
        if (!foundStudent.isLoggedIn()) {
            return ResponseEntity.badRequest().body(new GenericResponse<>("Student already logged out",  email));
        }

        foundStudent.setLoggedIn(false);

        return ResponseEntity.ok().body(new GenericResponse<>("Student logged out successfully", email));
    }

    @PostMapping("saveAll")
    public ResponseEntity<?> registerMultipleStudentsApi(
            @RequestBody List<StudentRegisterRequest> registerRequests
    ) {
        final List<Student> registeredStudents = new ArrayList<>();

        registerRequests.forEach(registerRequest -> {
            Optional<Student> optionalStudent = findByEmail(registerRequest.getEmail());

            if (optionalStudent.isPresent()) {
                registeredStudents.add(optionalStudent.get());
                return;
            }
            signupApi(registerRequest);
        });

        if (registeredStudents.isEmpty()) {
            return ResponseEntity.ok("Successfully all registeredStudents successfully!");
        }

        final List<StudentResponse> rejectedStudents = registeredStudents.stream()
                .map(Mappers::toStudentResponse)
                .toList();

        final GenericResponse<List<StudentResponse>> response =
                new GenericResponse<>("Those list are rejected to be inserted",  rejectedStudents);

        return ResponseEntity.ok(response);
    }
}
