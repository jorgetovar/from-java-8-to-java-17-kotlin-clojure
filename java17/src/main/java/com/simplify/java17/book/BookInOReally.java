package com.simplify.java17.book;

public class BookInOReally {

    public static boolean available(Book book) {
        return switch (book) {
            case FictionBook fictionBook -> fictionBook.available();
            case ProgrammingBook programmingBook -> programmingBook.exists();
            case PsychologicalBook psychologicalBook -> psychologicalBook.imAAvailable();
            case null, default -> false;
        };
    }
}
