package com.simplify.java17.book;

public class BookInOReally {

    public static boolean available(Book book) {
        if (book instanceof FictionBook) {
            return ((FictionBook) book).available();
        } else if (book instanceof ProgrammingBook) {
            return ((ProgrammingBook) book).exists();
        } else if (book instanceof PsychologicalBook) {
            return ((PsychologicalBook) book).imAAvailable();
        }
        return false;
    }
}
