package com.pioneers.librarymanagment;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class LibraryLoanSystem {
    public static void main(String[] args) {
        final LibraryManagement libraryManagement = new LibraryManagement(defaultBooks());

        libraryManagement.printLibraryBooks();

        System.out.println("--------------------------------------------------");

        libraryManagement.loanBook("java");
        libraryManagement.loanBook("Python");
        libraryManagement.loanBook("Java");
        libraryManagement.loanBook("javascript");

        System.out.println("--------------------------------------------------\n");

        libraryManagement.retrieveBook("c++");
        libraryManagement.retrieveBook("java");
        libraryManagement.retrieveBook("python");

        libraryManagement.loanBook("java");

        System.out.println("--------------------------------------------------\n");

        libraryManagement.printLoanedBooks();

        System.out.println("--------------------------------------------------\n");

        libraryManagement.printNonLoanedBooks();


    }

    private static List<Book> defaultBooks() {
        final List<Book> books = new LinkedList<>();

        books.add(
                new Book(
                        UUID.randomUUID(),
                        "C",
                        "A language for system programming."));
        books.add(
                new Book(
                        UUID.randomUUID(),
                        "C++",
                        "An object-oriented language based on C."));
        books.add(
                new Book(
                        UUID.randomUUID(),
                        "Python",
                        "A simple language used for many applications."));
        books.add(
                new Book(
                        UUID.randomUUID(),
                        "Java",
                        "An object-oriented language for application development."));
        books.add(
                new Book(
                        UUID.randomUUID(),
                        "JavaScript",
                        "A language used to create interactive web pages."));

        return books;
    }
}
