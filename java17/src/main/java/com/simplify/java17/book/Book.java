package com.simplify.java17.book;

public sealed interface Book permits Book.FictionBook, Book.ProgrammingBook, Book.PsychologicalBook {

  String title();

  String author();

  record FictionBook(String title, String author) implements Book {
    public boolean available() {
      return false;
    }
  }

  record ProgrammingBook(String title, String author) implements Book {
    public boolean exists() {
      return true;
    }
  }

  record PsychologicalBook(String title, String author) implements Book {
    public boolean imAAvailable() {
      return false;
    }
  }

}
