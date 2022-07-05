package com.simplify.ktlin.book

import org.springframework.stereotype.Service
import java.io.File

@Service
class BookParser {

    fun String.oneLiner(): String = this.trimIndent().replace("\n", "")

    enum class CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    enum class Category {
        fiction, programming, psychological
    }

    fun convertCsvToJson(inputCsv: String, outputJson: String): String {

        val lines = File(inputCsv).bufferedReader().readLines()
        return lines.map {
            it.split(",")
        }.map {
            val title = it[CsvColumns.TITLE.ordinal]
            val author = it[CsvColumns.AUTHOR.ordinal]
            val pages = it[CsvColumns.PAGES.ordinal].toInt()
            val category = Category.valueOf(it[CsvColumns.CATEGORY.ordinal])
            val (karma, book) = when (category) {
                Category.fiction -> {
                    Pair(25, Book.FictionBook(title, author))
                }
                Category.programming -> {
                    Pair(40, Book.ProgrammingBook(title, author))
                }
                Category.psychological -> {
                    Pair(30, Book.PsychologicalBook(title, author))
                }
            }

            """
            {"title":"$title",
            "author":"$author",
            "pages":$pages,
            "karma":$karma,
            "eBook":${BookInOReally.available(book)},
            "category":"$category"}
            """.oneLiner()
        }.toString()


    }
}