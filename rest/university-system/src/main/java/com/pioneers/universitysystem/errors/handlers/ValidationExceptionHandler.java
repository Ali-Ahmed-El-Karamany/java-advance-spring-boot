package com.pioneers.universitysystem.errors.handlers;

import com.pioneers.universitysystem.errors.models.ErrorDescription;
import com.pioneers.universitysystem.errors.models.ErrorResponse;
import com.pioneers.universitysystem.errors.models.StudentValidationError;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterErrors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static com.pioneers.universitysystem.utils.TimeHelper.currentTimeStamp;

@Slf4j
@RestControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse<List<ErrorDescription>> handleMethodArgumentNotValidException(
            final MethodArgumentNotValidException e
    ) {

        final String methodName = e.getParameter().getMethod().getName();

        final List<ErrorDescription> errors = e.getFieldErrors()
                .stream()
                .map(this::toErrorDescription)
                .toList();

        log.error("{}, Request validation failed: {}", methodName, errors);

        return new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), currentTimeStamp(), errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ErrorResponse<List<StudentValidationError>> handleHandlerMethodValidationException(
            final HandlerMethodValidationException e
    ) {
        final String methodName = e.getMethod().getName();

        final List<StudentValidationError> errors = e.getBeanResults().stream()
                .map(this::toStudentValidationError)
                .toList();

        log.error("{}, Request validation failed: {}", methodName, errors);

        return new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), currentTimeStamp(), errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResponse<List<ErrorDescription>> handleConstraintViolationException(
            final ConstraintViolationException e
    ) {
        final List<ErrorDescription> errors = e.getConstraintViolations().stream()
                .map(this::toErrorDescription)
                .toList();

        return new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), currentTimeStamp(), errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponse<ErrorDescription> handleMethodArgumentTypeMismatchException(
            final MethodArgumentTypeMismatchException e
    ) {
        final String methodName = e.getParameter().getMethod().getName();

        final ErrorDescription errors = new ErrorDescription(e.getName(), e.getCause().getMessage());

        log.error("{}, Request validation failed: {}", methodName, errors);

        return new ErrorResponse<>(HttpStatus.BAD_REQUEST.value(), currentTimeStamp(), errors);
    }

    private boolean isParameterKind(final ElementKind kind) {
        return ElementKind.PARAMETER.equals(kind);
    }

    private ErrorDescription toErrorDescription(final ConstraintViolation<?> constraintViolation) {
        AtomicReference<String> fieldName = new AtomicReference<>();

        constraintViolation.getPropertyPath().forEach(node -> {
            if (isParameterKind(node.getKind())) {
                fieldName.set(node.getName());
            }
        });

        return new ErrorDescription(fieldName.get(), constraintViolation.getMessage());
    }

    private ErrorDescription toErrorDescription(final FieldError fieldError) {
        return new ErrorDescription(fieldError.getField(), fieldError.getDefaultMessage());
    }

    private StudentValidationError toStudentValidationError(final ParameterErrors parameterErrors) {
        final int studentNumber = Optional.ofNullable(parameterErrors.getContainerIndex()).orElse(0);

        return new StudentValidationError(
                studentNumber + 1,
                parameterErrors.getFieldErrors()
                        .stream()
                        .map(this::toErrorDescription).toList());
    }
}
