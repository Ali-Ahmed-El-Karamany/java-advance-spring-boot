package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.models.dtos.requests.StudentUpdateRequest;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("findByEmail/{email}")
    public StudentResponse findByEmail(@PathVariable("email") String email) {
        final String methodName = "findByIdApi";
        log.info("{}, Finding student with email [{}]", methodName, email);

        final StudentResponse foundStudent = studentService.findByEmail(email);

        log.info("{}, Successfully found student with ID [{}]", methodName, email);

        return foundStudent;
    }

    @GetMapping("findAll")
    public List<StudentResponse> findAllApi() {
        final String methodName = "findAllApi";
        log.info("{}, Fetching registered students", methodName);

        final List<StudentResponse> allStudents = studentService.findAll();

        log.info("{}, Successfully fetched registered students", methodName);

        return allStudents;
    }

    @GetMapping("findById/{id}")
    public StudentResponse findByIdApi(@PathVariable("id") UUID id) {
        final String methodName = "findByIdApi";
        log.info("{}, Finding student with ID [{}]", methodName, id);

        final StudentResponse foundStudent = studentService.findById(id);

        log.info("{}, Successfully found student with ID [{}]", methodName, id);

        return foundStudent;
    }

    @GetMapping("findAllSucceededStudents")
    public List<StudentResponse> findAllSucceededStudentsApi() {
        final String methodName = "findAllSucceededStudentsApi";
        log.info("{}, Fetching succeeded Students", methodName);

        final List<StudentResponse> succeededStudents = studentService.findAllSucceeded();

        log.info("{}, Successfully fetched succeeded Students: {}", methodName, succeededStudents);

        return succeededStudents;
    }

    @PutMapping("update/{id}")
    public StudentResponse updateApi(
            @PathVariable("id") UUID id, @RequestBody @Valid StudentUpdateRequest updateRequest
    ) {
        final String methodName = "updateApi";
        log.info("{}, updating Student with email {} ", methodName, updateRequest.email());

        final StudentResponse updatedStudent = studentService.update(id, updateRequest);

        log.info("{}, Successfully updated student: {}", methodName, updatedStudent);

        return updatedStudent;
    }

    @DeleteMapping("delete/{id}")
    public String deleteApi(@PathVariable UUID id) {
        final String methodName = "deleteApi";
        log.info("{}, Deleting student with ID [{}]", methodName, id);

        final StudentResponse deletedStudent = studentService.delete(id);

        log.info("{}, Successfully deleted student with email {}", methodName, deletedStudent.email());

        return "Successfully deleted student with email: " + deletedStudent.email();
    }

    @DeleteMapping("deleteAll")
    public String deleteAllApi() {
        final String methodName = "deleteAllApi";
        log.info("{}, Deleting all students", methodName);

        studentService.deleteAll();

        log.info("{}, Successfully deleted all students", methodName);

        return "Successfully deleted all students";
    }
}
