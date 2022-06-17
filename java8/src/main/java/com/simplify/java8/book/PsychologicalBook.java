package com.simplify.java8.book;

public class PsychologicalBook extends Book {

    public PsychologicalBook(String title, String author) {
        super(title, author);
    }

    boolean imAAvailable() {
        return false;
    }
}
