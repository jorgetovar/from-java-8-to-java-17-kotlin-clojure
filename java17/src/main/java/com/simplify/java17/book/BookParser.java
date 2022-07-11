package com.simplify.java17.book;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

@Service
public class BookParser {

    public enum CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    public enum Category {
        fiction, programming, psychological
    }

    public String convertCsvToJson(String inputCsv, String outputJson) {

        String csvLine;
        var json = "[";

        try (var csvReader = new BufferedReader(new FileReader(inputCsv));
             var jsonWriter = new BufferedWriter(new FileWriter(outputJson))) {

            while ((csvLine = csvReader.readLine()) != null) {
                json = createJsonRecord(csvLine, json);
            }
            json = json.substring(0, json.length() - 1) + "]";

            jsonWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json.replace("\n", "");
    }


    private String createJsonRecord(String csvLine, String json) {
        String[] csvFields;
        csvFields = csvLine.split(",");

        var title = csvFields[CsvColumns.TITLE.ordinal()];
        var author = csvFields[CsvColumns.AUTHOR.ordinal()];
        var pages = Integer.parseInt(csvFields[CsvColumns.PAGES.ordinal()]);
        var category = Category.valueOf(csvFields[CsvColumns.CATEGORY.ordinal()]);
        var karma = 0;

        var book = switch (category) {
            case fiction -> {
                karma = 25;
                yield new FictionBook(title, author);
            }
            case programming -> {
                karma = 40;
                yield new ProgrammingBook(title, author);
            }
            case psychological -> {
                karma = 30;
                yield new PsychologicalBook(title, author);
            }
        };

        json += """
                {"title":"%s",
                "author":"%s",
                "pages":%s,
                "karma":%s,
                "eBook":%s,
                "rate":%s,
                "category":"%s"},""".formatted(title, author, pages,
                karma, BookInOReally.available(book), getRate(csvFields), category);
        return json;
    }

    private boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private BigDecimal getRate(String[] csvFields) {

        var sum = Arrays.stream(csvFields)
                .filter(this::isNumeric)
                .mapToDouble(Double::valueOf)
                .filter(e -> e <= 5 && e >= 0)
                .sum();
        return new BigDecimal(sum / 3).setScale(2, RoundingMode.HALF_UP);
    }

}
