package com.simplify.java17.book;

public final class PsychologicalBook extends Book {

    public PsychologicalBook(String title, String author) {
        super(title, author);
    }

    boolean imAAvailable() {
        return false;
    }
}
