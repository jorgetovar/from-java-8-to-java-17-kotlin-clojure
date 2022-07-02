package com.simplify.ktlin.book

sealed class Book(val title: String, val author: String) {

    class FictionBook(title: String, author: String) : Book(title, author) {
        fun available(): Boolean {
            return false
        }
    }

    class ProgrammingBook(title: String, author: String) : Book(title, author) {
        fun exists(): Boolean {
            return true
        }
    }

    class PsychologicalBook(title: String, author: String) : Book(title, author) {
        fun imAAvailable(): Boolean {
            return false
        }
    }
}