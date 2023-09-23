package com.simplify.java17.book;

import com.simplify.java17.book.Book.FictionBook;
import com.simplify.java17.book.Book.ProgrammingBook;
import com.simplify.java17.book.Book.PsychologicalBook;

public class BookInOReally {

    public static boolean available(Book book) {
        return switch (book) {
            case FictionBook fictionBook -> fictionBook.available();
            case ProgrammingBook programmingBook -> programmingBook.exists();
            case PsychologicalBook psychologicalBook -> psychologicalBook.imAAvailable();
        };
    }
}
