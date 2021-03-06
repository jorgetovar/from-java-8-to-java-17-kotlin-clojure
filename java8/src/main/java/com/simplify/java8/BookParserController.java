package com.simplify.java8;

import com.simplify.java8.book.BookParser;
import com.simplify.java8.book.BookResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookParserController {

    private final BookParser bookParser;

    public BookParserController(BookParser bookParser) {
        this.bookParser = bookParser;
    }

    @GetMapping("/csv-to-json")
    public BookResponse convertCsvToJson(@RequestParam String inputCsv, @RequestParam String outputJson) {
        return new BookResponse(bookParser.convertCsvToJson(inputCsv, outputJson), 1.0d);
    }

}
