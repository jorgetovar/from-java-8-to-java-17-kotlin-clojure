package com.simplify.ktlin

import com.simplify.ktlin.book.BookParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class KtlinApplicationTests {

    fun String.oneLiner(): String = this.trimIndent().replace("\n", "")

    @Test
    fun testConvertCsvToJson_WhenIsAProgrammingBook() {
        val expected = """
            [{"title":"Code Complete",
            "author":"Steve McConnell"
            ,"pages":960,
            "karma":40,"eBook":true,
            "category":"programming"}]
            """.oneLiner()
        val currentRelativePath = Paths.get("src/test/resources")
        val actual: String = BookParser().convertCsvToJson(
            "$currentRelativePath/programming-test.csv",
            "$currentRelativePath/programming-test.json"
        )
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun testConvertCsvToJson_WhenIsAFictionBook() {
        val expected = """
            [{"title":"Book Lovers",
            "author":"Emily Henry",
            "pages":1220,"karma":25,
            "eBook":false,
            "category":"fiction"}]
            """.oneLiner()
        val currentRelativePath = Paths.get("src/test/resources")
        val actual: String = BookParser().convertCsvToJson(
            "$currentRelativePath/fiction-test.csv",
            "$currentRelativePath/fiction-test.json"
        )
        Assertions.assertEquals(expected, actual)
    }

}
