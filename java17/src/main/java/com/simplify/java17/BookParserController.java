package com.simplify.java17;

import com.simplify.java17.book.BookParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class BookParserController {

    private final BookParser bookParser;

    public BookParserController(BookParser bookParser) {
        this.bookParser = bookParser;
    }

    @GetMapping("/csv-to-json")
    public String convertCsvToJson(@RequestParam String inputCsv, @RequestParam String outputJson) {
        return bookParser.convertCsvToJson(inputCsv, outputJson);
    }

}
