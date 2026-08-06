package com.pioneers.trainingsystem;

import java.util.*;

public class Student {
    private final UUID id;
    private final String studentName;
    private final int age;
    private final Set<String> studentCourses;

    public Student(UUID id, String studentName, int age) {
        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.studentCourses = new HashSet<>();
    }

    public boolean isMatched(String studentName) {
        return this.studentName.equalsIgnoreCase(studentName);
    }

    public void registerCourse(final String courseName) {
        studentCourses.add(courseName);
    }

    public int getNumberOfCourses() {
        return studentCourses.size();
    }

    public UUID getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getAge() {
        return age;
    }

    public Set<String> getStudentCourses() {
        return studentCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age
                && Objects.equals(id, student.id)
                && Objects.equals(studentName, student.studentName)
                && Objects.equals(studentCourses, student.studentCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentName, age, studentCourses);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", age=" + age +
                ", courses=" + studentCourses +
                '}';
    }
}
