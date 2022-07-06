package com.simplify.java17.book;

public class FictionBook extends Book {

    public FictionBook(String title, String author) {
        super(title, author);
    }

    boolean available() {
        return false;
    }
}
