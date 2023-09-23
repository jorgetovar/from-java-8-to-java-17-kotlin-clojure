package com.simplify.java17.book;

import com.simplify.java17.book.Book.FictionBook;
import com.simplify.java17.book.Book.ProgrammingBook;
import com.simplify.java17.book.Book.PsychologicalBook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.util.stream.Collectors.joining;

@Service
public class BookParser {

    private final Function<String, String[]> splitByComma = s -> s.split(",");
    private final UnaryOperator<String> intoOneLiner = s -> s.stripIndent().replace("\n", "");

    public enum CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    public enum Category {
        fiction, programming, psychological
    }

    public String convertCsvToJson(Path inputCsv, Path outputJson) {
        try (var csvReader = Files.newBufferedReader(inputCsv)) {
            return csvReader
                .lines()
                .map(splitByComma)
                .map(Arrays::asList)
                .map(this::createJsonRecord)
                .collect(joining(",", "[", "]"));

        } catch (IOException e) {
            // Not the best idea, but works.
          throw new RuntimeException(e);
        }
    }

    private String createJsonRecord(List<String> csvFields) {
        var ratings = csvFields.subList(CsvColumns.CATEGORY.ordinal() + 1, csvFields.size());

        var averageRate = ratings.stream()
            .mapToDouble(Double::parseDouble)
            .summaryStatistics()
            .getAverage();

        var title = csvFields.get(CsvColumns.TITLE.ordinal());
        var author = csvFields.get(CsvColumns.AUTHOR.ordinal());
        var pages = Integer.parseInt(csvFields.get(CsvColumns.PAGES.ordinal()));
        var category = Category.valueOf(csvFields.get(CsvColumns.CATEGORY.ordinal()));

        record BookWithKarma(int karma, Book book) {
        }

        var book = switch (category) {
            case fiction -> new BookWithKarma(25, new FictionBook(title, author));
            case programming -> new BookWithKarma(40, new ProgrammingBook(title, author));
            case psychological -> new BookWithKarma(30, new PsychologicalBook(title, author));
        };

        var formattedRate = String.format(Locale.ENGLISH, "%.2f", averageRate);

        return """
            {
            "title":"%s",
            "author":"%s",
            "pages":%s,
            "karma":%s,
            "eBook":%s,
            "rate":%s,
            "category":"%s"
            }
            """
            .formatted(
                book.book().title(),
                book.book().author(),
                pages,
                book.karma(),
                BookInOReally.available(book.book()),
                formattedRate,
                category
            ).transform(intoOneLiner);
    }

}
