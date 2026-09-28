package com.pioneers.universitysystem.services;

import com.pioneers.universitysystem.errors.exceptions.CredentialsExceptions;
import com.pioneers.universitysystem.errors.exceptions.StudentException;
import com.pioneers.universitysystem.models.dtos.requests.StudentUpdateRequest;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;
import com.pioneers.universitysystem.repositories.StudentRepository;
import com.pioneers.universitysystem.utils.Mappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.pioneers.universitysystem.utils.Mappers.toStudentResponse;
import static com.pioneers.universitysystem.utils.NameBuilder.buildFullName;
import static com.pioneers.universitysystem.utils.PasswordUtils.hashPassword;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<StudentResponse> findAll() throws StudentException {
        final String methodName = "findAll";

        final List<Student> registeredStudents = studentRepository.findAllSortedByAge();

        if (registeredStudents.isEmpty()) {
            final String logMessage = String.format("%s, No Students found.", methodName);

            throw new StudentException("No Student are Registered Yet!", logMessage);
        }

        log.debug("{}, Successfully fetched {} registered students from DB", methodName, registeredStudents.size());

        return registeredStudents.stream()
                .map(Mappers::toStudentResponse).toList();
    }

    @Override
    public StudentResponse findById(final UUID id) throws StudentException {
        final String methodName = "findById";

        final Student student = studentRepository.findById(id).orElseThrow(() -> {
            final String logMessage = String.format("%s, No Student registered with ID [%s]", methodName, id);

            return new StudentException("Student Not Found", logMessage);
        });

        log.debug("{}, Successfully found student with ID [{}]", methodName, id);

        return toStudentResponse(student);
    }

    @Override
    public List<StudentResponse> findAllSucceeded() throws StudentException {
        final String methodName = "findAllSucceeded";

        final List<Student> succeededStudents = studentRepository.findAllSucceeded();

        if (succeededStudents.isEmpty()) {
            final String logMessage =  String.format("%s, There are no succeeded students found", methodName);

            throw new StudentException("No succeeded students found", logMessage);
        }

        return succeededStudents.stream()
                .map(Mappers::toStudentResponse)
                .toList();

    }

    @Override
    public StudentResponse update(final UUID id, final StudentUpdateRequest updateRequest) throws StudentException {
        final String methodName = "update";

        final Student foundStudent = studentRepository.findById(id).orElseThrow(() -> {
            final String logMessage = String.format("%s, Student with ID [%s] is not registered", methodName, id);

            return new StudentException("Student Not Found", logMessage);
        });

        final String updatedFullName = buildFullName(updateRequest.firstName(), updateRequest.lastName());
        final String updatedHashedPassword;

        try {
            updatedHashedPassword = hashPassword(updateRequest.password());
        } catch (CredentialsExceptions e) {
            final String logMessage = String.format("%s, Cannot hash password for student with ID  [%s] due to: [%s]",
                    methodName, id, e.getMessage());

            throw new StudentException(e.getMessage(), logMessage);
        }

        foundStudent.setFullName(updatedFullName);
        foundStudent.setAge(updateRequest.age());
        foundStudent.setEmail(updateRequest.email());
        foundStudent.setPassword(updatedHashedPassword);
        foundStudent.setScore(updateRequest.score());

        studentRepository.update(id, foundStudent);

        log.debug("{}, Successfully updated student with ID [{}]", methodName, id);

        return toStudentResponse(foundStudent);
    }

    public StudentResponse delete(final UUID id) throws StudentException {
        final String methodName = "delete";

        final Student foundStudent = studentRepository.findById(id).orElseThrow(() -> {
            final String lgoMessage = String.format("%s, Cannot delete student with ID [%s]", methodName, id);

            return new StudentException("Student Not Registered", lgoMessage);
        });

        studentRepository.delete(id);

        log.debug("{}, Successfully deleted student with email {}", methodName, foundStudent.getEmail());

        return toStudentResponse(foundStudent);
    }

    @Override
    public void deleteAll() {
        final String  methodName = "deleteAll";

        studentRepository.deleteAll();

        log.debug("{}, Successfully deleted all students", methodName);
    }
}
