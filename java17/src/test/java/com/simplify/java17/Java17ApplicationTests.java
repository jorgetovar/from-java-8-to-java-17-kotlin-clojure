package com.simplify.java17;

import com.simplify.java17.book.BookParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Java17ApplicationTests {

    @Test
    public void testConvertCsvToJson_WhenIsAProgrammingBook() {

        var expected = """
            [{"title":"Code Complete",
            "author":"Steve McConnell"
            ,"pages":960,
            "karma":40,"eBook":true,
            "category":"programming"}]
            """.replace("\n", "");;
        var currentRelativePath = Paths.get("src/test/resources");
        var actual = new BookParser().convertCsvToJson(currentRelativePath + "/programming-test.csv",
                currentRelativePath + "/programming-test.json");
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertCsvToJson_WhenIsAFictionBook() {

        var expected = """
            [{"title":"Book Lovers",
            "author":"Emily Henry",
            "pages":1220,"karma":25,
            "eBook":false,
            "category":"fiction"}]
            """.replace("\n", "");;
        var currentRelativePath = Paths.get("src/test/resources");
        var actual = new BookParser().convertCsvToJson(currentRelativePath + "/fiction-test.csv",
                currentRelativePath + "/fiction-test.json");
        assertEquals(expected, actual);
    }

}
