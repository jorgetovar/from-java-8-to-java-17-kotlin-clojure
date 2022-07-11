package com.simplify.java8.book;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

import static com.simplify.java8.book.BookParser.CsvColumns.AUTHOR;
import static com.simplify.java8.book.BookParser.CsvColumns.CATEGORY;
import static com.simplify.java8.book.BookParser.CsvColumns.PAGES;
import static com.simplify.java8.book.BookParser.CsvColumns.TITLE;

@Service
public class BookParser {

    public enum CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    public enum Category {
        fiction, programming, psychological
    }

    public String convertCsvToJson(String inputCsv, String outputJson) {

        String csvLine, json = "[";


        try (BufferedReader csvReader = new BufferedReader(new FileReader(inputCsv));
             BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(outputJson))) {

            while ((csvLine = csvReader.readLine()) != null) {
                json = createJsonRecord(csvLine, json);
            }
            json = json.substring(0, json.length() - 1);
            json += "]";

            jsonWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    private String createJsonRecord(String csvLine, String json) {
        String[] csvFields;
        csvFields = csvLine.split(",");

        String title = csvFields[TITLE.ordinal()];
        String author = csvFields[AUTHOR.ordinal()];
        int pages = Integer.parseInt(csvFields[PAGES.ordinal()]);
        Category category = Category.valueOf(csvFields[CATEGORY.ordinal()]);
        int karma = 0;
        Book book = null;
        switch (category) {
            case fiction:
                karma = 25;
                book = new FictionBook(title, author);
                break;
            case programming:
                karma = 40;
                book = new ProgrammingBook(title, author);
                break;
            case psychological:
                karma = 30;
                book = new PsychologicalBook(title, author);
                break;
        }
        json += "{";
        json += "\"title\":\"" + title + "\",";
        json += "\"author\":\"" + author + "\",";
        json += "\"pages\":" + pages + ",";
        json += "\"karma\":" + karma + ",";
        json += "\"eBook\":" + BookInOReally.available(book) + ",";
        json += "\"rate\":" + getRate(csvFields) + ",";
        json += "\"category\":\"" + category + "\"";
        json += "},";
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

        double sum = Arrays.stream(csvFields)
                .filter(this::isNumeric)
                .mapToDouble(Double::valueOf)
                .filter(e -> e <= 5 && e >= 0)
                .sum();
        return new BigDecimal(sum / 3).setScale(2, RoundingMode.HALF_UP);
    }

}
