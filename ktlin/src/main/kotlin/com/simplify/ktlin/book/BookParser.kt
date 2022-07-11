package com.simplify.ktlin.book

import org.springframework.stereotype.Service
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class BookParser {

    private fun String.oneLiner(): String = this.trimIndent().replace("\n", "")

    enum class CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    enum class Category {
        fiction, programming, psychological
    }

    fun convertCsvToJson(inputCsv: String, outputJson: String): String {

        val lines = File(inputCsv).bufferedReader().readLines()
        return lines
            .map { it.split(",") }
            .map { createJsonRecord(it) }
            .toString()


    }

    private fun createJsonRecord(it: List<String>): String {
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

        return """
                {"title":"$title",
                "author":"$author",
                "pages":$pages,
                "karma":$karma,
                "eBook":${BookInOReally.available(book)},
                "rate":${getRate(it)},
                "category":"$category"}
                """.oneLiner()
    }

    private fun isNumeric(str: String): Boolean {
        return str.toDoubleOrNull() != null
    }

    private fun getRate(rates: List<String>): BigDecimal {
        val sum = rates
            .filter { this.isNumeric(it) }
            .map { it.toDouble() }
            .filter { it in 0.0..5.0 }.sum()
        return BigDecimal(sum / 3).setScale(2, RoundingMode.HALF_UP)
    }
}