package com.simplify.java8.book;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

@Service
public class BookParser {

    private final Function<String, String[]> splitByComma = s -> s.split(",");

    public enum CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    public enum Category {
        fiction, programming, psychological
    }

    public String convertCsvToJson(Path inputCsv, Path outputJson) {
        try (BufferedReader csvReader = Files.newBufferedReader(inputCsv)) {
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
        List<String> ratings = csvFields.subList(CsvColumns.CATEGORY.ordinal() + 1, csvFields.size());

        Double averageRate = ratings.stream()
            .mapToDouble(Double::parseDouble)
            .summaryStatistics()
            .getAverage();

        String title = csvFields.get(CsvColumns.TITLE.ordinal());
        String author = csvFields.get(CsvColumns.AUTHOR.ordinal());
        int pages = Integer.parseInt(csvFields.get(CsvColumns.PAGES.ordinal()));
        Category category = Category.valueOf(csvFields.get(CsvColumns.CATEGORY.ordinal()));

        BookWithKarma book = convertToBook(category, title, author);

        return "{" + "\"title\"" + ":" + '"' + title + '"' + "," +
               "\"author\"" + ":" + '"' + author + '"' + "," +
               "\"pages\"" + ":" + pages + "," +
               "\"karma\"" + ":" + book.getKarma() + "," +
               "\"eBook\"" + ":" + BookInOReally.available(book.getBook()) + "," +
               "\"rate\"" + ":" + String.format(Locale.US, "%.2f", averageRate) + "," +
               "\"category\"" + ":" + '"' + category + '"' +
               "}";
    }

    static class BookWithKarma {
        private Integer karma;
        private Book book;

        BookWithKarma(Integer karma, Book book) {
            this.karma = karma;
            this.book = book;
        }

        Integer getKarma() {return karma;}

        Book getBook() {return book;}
    }

    private BookWithKarma convertToBook(Category category, String title, String author) {
        switch (category) {
            case fiction: return new BookWithKarma(25, new FictionBook(title, author));
            case programming: return new BookWithKarma(40, new ProgrammingBook(title, author));
            case psychological: return new BookWithKarma(30, new PsychologicalBook(title, author));
            default:
                throw new IllegalStateException();
        }
    }

}
