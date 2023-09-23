package com.simplify.ktlin.book

import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.io.path.Path
import kotlin.io.path.readLines

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
        return Path(inputCsv)
            .readLines()
            .map { it.split(",") }
            .map { createJsonRecord(it) }
            .toString()
    }

    private fun createJsonRecord(it: List<String>): String {
        val title = it[CsvColumns.TITLE.ordinal]
        val author = it[CsvColumns.AUTHOR.ordinal]
        val pages = it[CsvColumns.PAGES.ordinal].toInt()
        val category = Category.valueOf(it[CsvColumns.CATEGORY.ordinal])
        val rate = getRate(it.subList(CsvColumns.CATEGORY.ordinal + 1, it.size).map { it.toDouble() })

        val (karma, book) = when (category) {
            Category.fiction -> Pair(25, Book.FictionBook(title, author))
            Category.programming -> Pair(40, Book.ProgrammingBook(title, author))
            Category.psychological -> Pair(30, Book.PsychologicalBook(title, author))
        }

        val formattedRate = String.format(Locale.ENGLISH, "%.2f", rate)

        return """
                {"title":"$title",
                "author":"$author",
                "pages":$pages,
                "karma":$karma,
                "eBook":${BookInOReally.available(book)},
                "rate":${formattedRate},
                "category":"$category"}
                """.oneLiner()
    }

    private fun getRate(rates: List<Double>): BigDecimal {
        return BigDecimal(rates.sum() / 3.0).setScale(2, RoundingMode.HALF_UP)
    }
}