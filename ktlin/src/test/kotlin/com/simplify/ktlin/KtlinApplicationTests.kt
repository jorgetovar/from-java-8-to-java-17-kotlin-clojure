package com.simplify.ktlin

import com.simplify.ktlin.book.BookParser
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.nio.file.Path
import java.nio.file.Paths

class KtlinApplicationTests {

    private fun String.oneLiner(): String = this.trimIndent().replace("\n", "")
    private val currentRelativePath: Path = Paths.get("src/test/resources")

    @Test
    fun testConvertCsvToJson_WhenIsAProgrammingBook() {
        val expected = """
            [{"title":"Code Complete",
            "author":"Steve McConnell"
            ,"pages":960,
            "karma":40,
            "eBook":true,
            "rate":3.83,
            "category":"programming"}]
            """.oneLiner()
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
            "rate":4.00,
            "category":"fiction"}]
            """.oneLiner()

        val actual: String = BookParser().convertCsvToJson(
            "$currentRelativePath/fiction-test.csv",
            "$currentRelativePath/fiction-test.json"
        )
        Assertions.assertEquals(expected, actual)
    }

}
