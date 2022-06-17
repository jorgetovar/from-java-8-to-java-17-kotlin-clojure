package com.simplify.java8;

import com.simplify.java8.service.BookParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Java8ApplicationTests {

    @Test
    public void testConvertCsvToJson() throws Exception {

        String expected = "[{\"title\":\"Code Complete\"," +
                "\"author\":\"Steve McConnell\"," +
                "\"pages\":960," +
                "\"category\":\"nonfiction\"}]";
        Path currentRelativePath = Paths.get("src/test/resources");
        String actual = new BookParser().convertCsvToJson(currentRelativePath + "/test.csv",
                currentRelativePath + "/test-output.json");
        assertEquals(expected, actual);
    }

}
