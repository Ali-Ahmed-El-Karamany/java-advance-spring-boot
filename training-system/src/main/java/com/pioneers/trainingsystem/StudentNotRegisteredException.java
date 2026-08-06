package com.pioneers.trainingsystem;

public class StudentNotRegisteredException extends RuntimeException {
    public StudentNotRegisteredException(String message) {
        super(message);
    }
}
