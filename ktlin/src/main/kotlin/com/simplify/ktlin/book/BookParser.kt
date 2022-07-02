package com.simplify.ktlin.book

import org.springframework.stereotype.Service
import java.io.*

@Service
class BookParser {

    enum class CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    enum class Category {
        fiction, programming, psychological
    }

    fun convertCsvToJson(inputCsv: String, outputJson: String): String {
        return ""
    }
}