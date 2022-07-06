package com.simplify.java17.book;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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

            String[] csvFields;

            while ((csvLine = csvReader.readLine()) != null) {
                csvFields = csvLine.split(",");

                var title = csvFields[CsvColumns.TITLE.ordinal()];
                var author = csvFields[CsvColumns.AUTHOR.ordinal()];
                var pages = Integer.parseInt(csvFields[CsvColumns.PAGES.ordinal()]);
                Category category = Category.valueOf(csvFields[CsvColumns.CATEGORY.ordinal()]);
                var karma = 0;
                Book book = null;
                switch (category) {
                    case fiction -> {
                        karma = 25;
                        book = new FictionBook(title, author);
                    }
                    case programming -> {
                        karma = 40;
                        book = new ProgrammingBook(title, author);
                    }
                    case psychological -> {
                        karma = 30;
                        book = new PsychologicalBook(title, author);
                    }
                }

                json += """
                        {"title":"%s",
                        "author":"%s",
                        "pages":%s,
                        "karma":%s,
                        "eBook":%s,
                        "category":"%s"},""".formatted(title, author, pages, karma, BookInOReally.available(book), category);


            }
            json = json.substring(0, json.length() - 1) + "]";


            jsonWriter.write(json);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json.replace("\n", "");
    }

}