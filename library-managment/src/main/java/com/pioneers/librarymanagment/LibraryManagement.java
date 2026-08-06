package com.pioneers.librarymanagment;

import java.util.List;

public class LibraryManagement {
    private final List<Book> books;

    public LibraryManagement(final List<Book> books) {
        this.books = books;
    }

    public void printLibraryBooks() {
        if (books.isEmpty()) {
            System.out.println("The Library has no books");
        }
        books.forEach(book -> System.out.println(book.printBookInfo()));
    }

    public void printLoanedBooks() {
        if (books.isEmpty()) {
            System.out.println("The Library has no books");
        }
        System.out.println("_______________ All Loaned Books _______________\n");

        books.stream()
                .filter(Book::isLoaned)
                .forEach(System.out::println);
    }

    public void printNonLoanedBooks() {
        if (books.isEmpty()) {
            System.out.println("The Library has no books");
        }
        System.out.println("_______________ All Non-Loaned Books _______________\n");

        books.stream()
                .filter(book -> !book.isLoaned())
                .forEach(System.out::println);
    }

    public void loanBook(final String bookName) {
        try {
            Book foundBook = findBookByName(bookName);

            foundBook.loanBook();
        } catch (BookLoanException | BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void retrieveBook(final String bookName) {
        try {
            Book foundBook = findBookByName(bookName);

            foundBook.retrieveBook();
        } catch (BookLoanException | BookNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Book findBookByName(final String bookName) throws BookNotFoundException {
        return books.stream()
                .filter(book -> book.getName().equalsIgnoreCase(bookName))
                .findFirst().orElseThrow(() -> new BookNotFoundException(bookName + " book not found in the library"));
    }
}
