package com.simplify.ktlin.book


object BookInOReally {

    fun available(book: Book): Boolean {
        return when (book) {
            is Book.FictionBook -> {
                book.available()
            }
            is Book.ProgrammingBook -> {
                book.exists()
            }
            is Book.PsychologicalBook -> {
                book.imAAvailable()
            }
        }
    }
}




