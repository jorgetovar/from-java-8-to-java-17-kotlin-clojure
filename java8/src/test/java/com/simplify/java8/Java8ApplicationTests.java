package com.simplify.java8;

import com.simplify.java8.book.BookParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class Java8ApplicationTests {

    private static final Path RESOURCES_PATH = Paths.get("src/test/resources");

    @InjectMocks
    private BookParser bookParser;

    @Test
    void testConvertCsvToJson_WhenIsAProgrammingBook() {

        String expected = "[{\"title\":\"Code Complete\"," +
                "\"author\":\"Steve McConnell\"," +
                "\"pages\":960," + "\"karma\":40," +
                "\"eBook\":true," + "\"rate\":3.83," +
                "\"category\":\"programming\"}]";
        String actual = bookParser.convertCsvToJson(RESOURCES_PATH.resolve("programming-test.csv"),
            RESOURCES_PATH.resolve("programming-test.json"));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testConvertCsvToJson_WhenIsAFictionBook() {

        String expected = "[{\"title\":\"Book Lovers\"," +
                "\"author\":\"Emily Henry\"," +
                "\"pages\":1220," + "\"karma\":25," +
                "\"eBook\":false," + "\"rate\":4.00," +
                "\"category\":\"fiction\"}]";
        String actual = bookParser.convertCsvToJson(RESOURCES_PATH.resolve("fiction-test.csv"),
            RESOURCES_PATH.resolve("fiction-test.json"));
        assertThat(actual).isEqualTo(expected);
    }

}
