package com.simplify.java8.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static com.simplify.java8.service.BookParser.CsvColumns.AUTHOR;
import static com.simplify.java8.service.BookParser.CsvColumns.CATEGORY;
import static com.simplify.java8.service.BookParser.CsvColumns.PAGES;
import static com.simplify.java8.service.BookParser.CsvColumns.TITLE;

@Service
public class BookParser {

    public enum CsvColumns {
        TITLE, AUTHOR, PAGES, CATEGORY
    }

    public enum Category {
        fiction, nonfiction
    }

    public String convertCsvToJson(String inputCsv, String outputJson) {

        String csvLine, json = "[";


        try (BufferedReader csvReader = new BufferedReader(new FileReader(inputCsv));
             BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(outputJson))) {

            String[] csvFields;

            while ((csvLine = csvReader.readLine()) != null) {
                csvFields = csvLine.split(",");

                String title = csvFields[TITLE.ordinal()];
                String author = csvFields[AUTHOR.ordinal()];
                int pages = Integer.parseInt(csvFields[PAGES.ordinal()]);
                Category category = Category.valueOf(csvFields[CATEGORY.ordinal()]);

                json += "{";
                json += "\"title\":\"" + title + "\",";
                json += "\"author\":\"" + author + "\",";
                json += "\"pages\":" + pages + ",";
                json += "\"category\":\"" + category + "\"";
                json += "},";
            }
            json = json.substring(0, json.length() - 1);
            json += "]";

            jsonWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}
