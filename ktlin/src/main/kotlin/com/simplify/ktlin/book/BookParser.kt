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
        val jsonList: List<String> = lines.map {
            it.split(",")
        }.map {
            val title = it[CsvColumns.TITLE.ordinal]
            val author = it[CsvColumns.AUTHOR.ordinal]
            val pages = it[CsvColumns.PAGES.ordinal].toInt()
            val category = Category.valueOf(it[CsvColumns.CATEGORY.ordinal])
            val karma = when (category) {
                Category.fiction -> {
                    25
                }
                Category.programming -> {
                    40
                }
                Category.psychological -> {
                    30
                }
            }

            val book = when (category) {
                Category.fiction -> {
                    Book.FictionBook(title, author)
                }
                Category.programming -> {
                    Book.ProgrammingBook(title, author)
                }
                Category.psychological -> {
                    Book.PsychologicalBook(title, author)
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
        }
        return jsonList.toString()

    }
}