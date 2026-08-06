package com.pioneers.trainingsystem;

import java.util.*;

public class TrainingSystem {
    public static void main(String[] args) {
        TrainingCenter trainingCenter = new TrainingCenter(registerStudents(), availableCourses());

        trainingCenter.printAvailableCourses();

        System.out.println("------------------------------------");

        trainingCenter.registerStudentCourse("Ali", "Programming fundamentals");
        trainingCenter.registerStudentCourse("Ali", "advanced programming");
        trainingCenter.registerStudentCourse("Ali", "Backend");
        trainingCenter.registerStudentCourse("Ali", "frontend");

        trainingCenter.registerStudentCourse("Mohamed", "Programming fundamentals");
        trainingCenter.registerStudentCourse("Mohamed", "advanced programming");

        trainingCenter.registerStudentCourse("omar", "Math");

        trainingCenter.registerStudentCourse("ahmed", "english");
        trainingCenter.registerStudentCourse("ahmed", "DevOps");
        trainingCenter.registerStudentCourse("ahmed", "math");

        System.out.println("------------------------------------");

        trainingCenter.printStudentsInfo();

        try {
            final Student studentWithMaxScore = trainingCenter.getStudentWithMaxCourses();
            trainingCenter.printStudentCourses(studentWithMaxScore);
        } catch (StudentNotRegisteredException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<Student> registerStudents() {
        final List<Student> students = new ArrayList<>();

        students.add(new Student(UUID.randomUUID(), "Ali", 27));
        students.add(new Student(UUID.randomUUID(), "Ahmed", 24));
        students.add(new Student(UUID.randomUUID(), "Omar", 30));
        students.add(new Student(UUID.randomUUID(), "Mohamed", 22));

        return students;
    }

    private static Set<String> availableCourses() {
        final Set<String> courses = new HashSet<>();

        courses.add("English");
        courses.add("Programming Fundamentals");
        courses.add("Advanced Programming");
        courses.add("Backend");
        courses.add("Frontend");
        courses.add("DevOps");

        return courses;
    }
}
