package com.simplify.java17;

import com.simplify.java17.book.BookParser;
import com.simplify.java17.book.BookResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

@RestController
class BookParserController {

    private final BookParser bookParser;

    BookParserController(BookParser bookParser) {
        this.bookParser = bookParser;
    }

    @GetMapping("/csv-to-json")
    BookResponse convertCsvToJson(@RequestParam String inputCsv, @RequestParam String outputJson) {
        return new BookResponse(bookParser.convertCsvToJson(Path.of(inputCsv), Path.of(outputJson)), 1.0d);
    }

}
