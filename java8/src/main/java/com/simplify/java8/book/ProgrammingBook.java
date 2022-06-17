package com.simplify.java8.book;

public class ProgrammingBook extends Book {

    public ProgrammingBook(String title, String author) {
        super(title, author);
    }

    boolean exists() {
        return true;
    }
}
