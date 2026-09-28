package com.pioneers.universitysystem.controllers;

import com.pioneers.universitysystem.errors.exceptions.StudentException;
import com.pioneers.universitysystem.models.dtos.requests.StudentUpdateRequest;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.services.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    @GetMapping("findAll")
    public ResponseEntity<?> findAllApi() {

        final String methodName =  "findAllApi";
        log.info("{}, Fetching registered students",methodName);

        final List<StudentResponse> allStudents;

        try {
            allStudents = studentService.findAll();

        } catch (StudentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        log.info("{}, Successfully fetched registered students", methodName);

        return ResponseEntity.ok(allStudents);
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<?> findByIdApi(@PathVariable("id") UUID id) {
        final String methodName =  "findByIdApi";
        log.info("{}, Finding student with ID [{}]",methodName, id);

        try {
            final StudentResponse foundStudent = studentService.findById(id);

            log.info("{}, Successfully found student with ID [{}]",methodName, id);

            return ResponseEntity.ok(foundStudent);
        } catch (StudentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("findAllSucceededStudents")
    public ResponseEntity<?> findAllSucceededStudentsApi() {
        final String methodName =  "findAllSucceededStudentsApi";
        log.info("{}, Fetching succeeded Students",methodName);

        final List<StudentResponse> succeededStudents;

        try {
            succeededStudents = studentService.findAllSucceeded();

            log.info("{}, Successfully fetched succeeded Students: {}", methodName,  succeededStudents);
        } catch (StudentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok(succeededStudents);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateApi(
            @PathVariable("id") UUID id, @RequestBody StudentUpdateRequest updateRequest
    ) {
        final String methodName =  "updateApi";
        log.info("{}, updating Student with email {} ", methodName, updateRequest.email());

        final StudentResponse updatedStudent;
        try {
            updatedStudent = studentService.update(id,updateRequest);

            log.info("{}, Successfully updated student: {}",methodName, updatedStudent);
        } catch (StudentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteApi(@PathVariable UUID id) {
        final String methodName =  "deleteApi";
        log.info("{}, Deleting student with ID [{}]",methodName, id);

        final StudentResponse deletedStudent;
        try {
            deletedStudent = studentService.delete(id);

            log.info("{}, Successfully deleted student with email {}",methodName, deletedStudent.email());
        } catch (StudentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok("Successfully deleted student with email: " + deletedStudent.email());
    }

    @DeleteMapping("deleteAll")
    public ResponseEntity<String> deleteAllApi() {
        final String methodName =  "deleteAllApi";
        log.info("{}, Deleting all students",methodName);

        studentService.deleteAll();

        log.info("{}, Successfully deleted all students",methodName);

        return ResponseEntity.ok("Successfully deleted all students");
    }
}
