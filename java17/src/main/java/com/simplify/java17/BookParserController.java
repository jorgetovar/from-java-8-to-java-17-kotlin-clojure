package com.simplify.java17;

import com.simplify.java17.book.BookParser;
import com.simplify.java17.book.BookResponse;
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
