package com.simplify.java17;

import com.simplify.java17.book.BookParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class Java17ApplicationTests {

    @InjectMocks
    private BookParser bookParser;

    private static final Path RESOURCES_PATH = Paths.get("src/test/resources");

    @Test
    void testConvertCsvToJson_WhenIsAProgrammingBook() {
        var expected = """
            [
            {"title":"Code Complete",
            "author":"Steve McConnell",
            "pages":960,
            "karma":40,
            "eBook":true,
            "rate":3.83,
            "category":"programming"}
            ]
            """.transform(intoOneLiner);

        var actual = bookParser.convertCsvToJson(RESOURCES_PATH.resolve("programming-test.csv"),
            RESOURCES_PATH.resolve("programming-test.json"));
        assertEquals(expected, actual);
    }

    private final UnaryOperator<String> intoOneLiner = s -> s.stripIndent().replace("\n", "");

    @Test
    void testConvertCsvToJson_WhenIsAFictionBook() {
        var expected = """
            [{"title":"Book Lovers",
            "author":"Emily Henry",
            "pages":1220,
            "karma":25,
            "eBook":false,
            "rate":4.00,
            "category":"fiction"}]
            """.transform(intoOneLiner);
        var actual = bookParser.convertCsvToJson(RESOURCES_PATH.resolve("fiction-test.csv"),
            RESOURCES_PATH.resolve("fiction-test.json"));
        assertEquals(expected, actual);
    }

}
