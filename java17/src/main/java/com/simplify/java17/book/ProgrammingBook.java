package com.simplify.java17.book;

public final class ProgrammingBook extends Book {

    public ProgrammingBook(String title, String author) {
        super(title, author);
    }

    boolean exists() {
        return true;
    }
}
