package com.simplify.java8;

import com.simplify.java8.book.BookParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Java8ApplicationTests {

    @Test
    public void testConvertCsvToJson_WhenIsAProgrammingBook() {

        String expected = "[{\"title\":\"Code Complete\"," +
                "\"author\":\"Steve McConnell\"," +
                "\"pages\":960," + "\"karma\":40," +
                "\"eBook\":true," + "\"rate\":3.83," +
                "\"category\":\"programming\"}]";
        Path currentRelativePath = Paths.get("src/test/resources");
        String actual = new BookParser().convertCsvToJson(currentRelativePath + "/programming-test.csv",
                currentRelativePath + "/programming-test.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertCsvToJson_WhenIsAFictionBook() {

        String expected = "[{\"title\":\"Book Lovers\"," +
                "\"author\":\"Emily Henry\"," +
                "\"pages\":1220," + "\"karma\":25," +
                "\"eBook\":false," + "\"rate\":4.00," +
                "\"category\":\"fiction\"}]";
        Path currentRelativePath = Paths.get("src/test/resources");
        String actual = new BookParser().convertCsvToJson(currentRelativePath + "/fiction-test.csv",
                currentRelativePath + "/fiction-test.json");
        assertEquals(expected, actual);
    }

}
