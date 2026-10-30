package com.pioneers.universitysystem.services;

import com.pioneers.universitysystem.errors.exceptions.*;
import com.pioneers.universitysystem.models.dtos.requests.StudentLoginRequest;
import com.pioneers.universitysystem.models.dtos.requests.StudentRegisterRequest;
import com.pioneers.universitysystem.models.dtos.responses.GenericResponse;
import com.pioneers.universitysystem.models.dtos.responses.StudentResponse;
import com.pioneers.universitysystem.models.entities.Student;
import com.pioneers.universitysystem.repositories.StudentRepository;
import com.pioneers.universitysystem.utils.Mappers.StudentMappers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

import static com.pioneers.universitysystem.utils.NameBuilder.buildFullName;
import static com.pioneers.universitysystem.utils.PasswordUtils.hashPassword;
import static com.pioneers.universitysystem.utils.PasswordUtils.matchPassword;
import static com.pioneers.universitysystem.utils.StudentBuilder.buildRegisteredStudent;

@Slf4j
@Service
@Validated
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final StudentRepository studentRepository;

    @Override
    public void signup(final StudentRegisterRequest registerRequest) throws RegistrationException {
        final String methodName = "signup";

        studentRepository.findExistingEmail(registerRequest.email()).ifPresent(student -> {
            final String logMessage = String.format("%s, Registration failed: [%s] is already registered",
                    methodName, student.getEmail());

            throw new RegistrationException(
                    String.format("Student with email [%s] is already registered", registerRequest.email()), logMessage);
        });

        final String fullName = buildFullName(registerRequest.firstName(), registerRequest.lastName());

        final String hashedPassword = hashPassword(registerRequest.password());

        final Student student = buildRegisteredStudent(fullName, registerRequest.age(),
                registerRequest.email(), hashedPassword, registerRequest.addressRequest());

        studentRepository.save(student);

        log.debug("{}, {} successfully registered with email [{}]",
                methodName, student.getFullName(), student.getEmail());
    }

    @Override
    public void login(final StudentLoginRequest loginRequest) throws LoginException {

        final String methodName = "login";

        final Student foundStudent = studentRepository.findByEmail(loginRequest.email())
                .orElseThrow(() -> {
                    final String logMessage = String.format("%s, Student with email [%s] is not registered",
                            methodName, loginRequest.email());

                    return new LoginException("Student is not registered", logMessage);
                });

        matchPassword(loginRequest.password(), foundStudent.getPassword());

        if (foundStudent.isLoggedIn()) {
            final String logMessage = String.format("%s, Student with email [%s] already logged in",
                    methodName, loginRequest.email());

            throw new LoginException("Student already logged in", logMessage);
        }

        foundStudent.login();

        studentRepository.update(foundStudent);

        log.debug("{}, Student with email: {} logged in successfully", methodName, foundStudent.getEmail());
    }

    @Override
    public void logout(final String email) throws LogoutException {

        final String methodName = "logout";

        final Student foundStudent = studentRepository.findByEmail(email)
                .orElseThrow(() -> {
                    final String logMessage = String.format("%s, Student with email [%s] is not registered",
                            methodName, email);

                    return new LogoutException("Student not registered", logMessage);
                });

        if (!foundStudent.isLoggedIn()) {
            final String logMessage = String.format("%s, Student with email [%s] already logged out",
                    methodName, email);

            throw new LogoutException("Student already logged out", logMessage);
        }

        foundStudent.logout();

        studentRepository.update(foundStudent);

        log.debug("{}, Student with email: {} logged out successfully", methodName, foundStudent.getEmail());
    }

    @Override
    public GenericResponse<List<StudentResponse>> saveAll(
            final List<StudentRegisterRequest> registerRequests
    ) throws RegistrationException {

        final String methodName = "saveAll";

        final List<Student> registeredStudents = new ArrayList<>();

        registerRequests.forEach(registerRequest -> {
            studentRepository.findByEmail(registerRequest.email())
                    .ifPresentOrElse(registeredStudents::add, () -> signup(registerRequest));
        });

        if (registeredStudents.isEmpty()) {
            log.debug("Successfully registered all students");

            return new GenericResponse<>("Successfully registered all students",
                    registerRequests.stream().map(StudentMappers::toStudentResponse).toList());
        }

        final List<StudentResponse> rejectedStudents = registeredStudents.stream()
                .map(StudentMappers::toStudentResponse)
                .toList();

        log.debug("Those students are already registered {}", rejectedStudents);
        return new GenericResponse<>("Those students are already registered registered", rejectedStudents);
    }
}
