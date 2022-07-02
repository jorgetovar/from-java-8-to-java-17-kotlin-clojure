package com.simplify.ktlin

import com.simplify.ktlin.book.BookParser
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException


@RestController
class BookParserController(private val bookParser: BookParser) {

    @GetMapping("/csv-to-json")
    fun convertCsvToJson(@RequestParam inputCsv: String, @RequestParam outputJson: String): String =
            bookParser.convertCsvToJson(inputCsv, outputJson)
}