package com.pioneers.trainingsystem;

import java.util.*;

public class TrainingCenter {
    private final List<Student> students;
    private final Set<String> trainingCourses;

    TrainingCenter(final List<Student> students, final Set<String> courses) {
        this.students = students;
        this.trainingCourses = courses;
    }

    public void printAvailableCourses() {
        System.out.println("--------------Available courses--------------\n");
        trainingCourses.forEach(System.out::println);
    }

    public void registerStudent(final Student student) {
        students.add(student);
    }

    public void registerStudentCourse(final String studentName, final String courseName) {
        try {
            final Student foundStudent = findStudent(studentName);
            final String foundCourse = findCourse(courseName);

            foundStudent.registerCourse(foundCourse);
        } catch (CourseNotFoundException | StudentNotRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    public Student getStudentWithMaxCourses() throws StudentNotRegisteredException {
        int maxCourses = students.stream()
                .mapToInt(Student::getNumberOfCourses)
                .max()
                .orElseThrow(() -> new StudentNotRegisteredException("No registered Students"));

        return students.stream()
                .filter(student -> student.getNumberOfCourses() == maxCourses)
                .findFirst().orElseThrow(() -> new StudentNotRegisteredException("No registered Students"));
    }

    public void printStudentsInfo() {
        students.forEach(System.out::println);
    }

    public void printStudentCourses(final Student student) {
        student.getStudentCourses().forEach(System.out::println);
    }

    private Student findStudent(final String studentName) throws StudentNotRegisteredException {
        return students.stream()
                .filter(foundStudent -> foundStudent.isMatched(studentName))
                .findFirst()
                .orElseThrow(() -> new StudentNotRegisteredException(
                        "No registered student with name " + studentName));
    }

    private String findCourse(String courseName) throws CourseNotFoundException {
        return trainingCourses.stream()
                .filter(course -> course.equalsIgnoreCase(courseName)).findFirst()
                .orElseThrow(() -> new CourseNotFoundException(courseName + " Course Not Found"));
    }
}
