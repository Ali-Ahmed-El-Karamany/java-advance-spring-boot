package com.pioneers.librarymanagment;

import java.util.Objects;
import java.util.UUID;

public class Book {
    private final UUID id;
    private final String name;
    private final String description;
    private boolean isLoaned;

    public Book(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String printBookInfo() {
        return "name: '" + name + '\'' +
                ", description: '" + description + '\'';
    }

    public void loanBook() throws BookLoanException {
        if (this.isLoaned) {
            throw new BookLoanException(this.getName() + " Book" + " is already loaned");
        }
        this.isLoaned = true;
    }

    public void retrieveBook() {
        if (!this.isLoaned) {
            throw new BookLoanException(this.getName() + " Book" + " is not loaned");
        }
        this.isLoaned = false;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id
                && isLoaned == book.isLoaned
                && Objects.equals(name, book.name)
                && Objects.equals(description, book.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isLoaned);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", isLoaned=" + isLoaned +
                '}';
    }
}
