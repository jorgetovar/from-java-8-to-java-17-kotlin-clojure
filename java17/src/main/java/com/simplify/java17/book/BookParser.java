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

        String csvLine, json = "[";


        try (BufferedReader csvReader = new BufferedReader(new FileReader(inputCsv));
             BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(outputJson))) {

            String[] csvFields;

            while ((csvLine = csvReader.readLine()) != null) {
                csvFields = csvLine.split(",");

                String title = csvFields[CsvColumns.TITLE.ordinal()];
                String author = csvFields[CsvColumns.AUTHOR.ordinal()];
                int pages = Integer.parseInt(csvFields[CsvColumns.PAGES.ordinal()]);
                Category category = Category.valueOf(csvFields[CsvColumns.CATEGORY.ordinal()]);
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
