package com.simplify.java8.book;

public class BookResponse {

    private final String json;
    private final Double version;

    public BookResponse(String json, Double version) {
        this.json = json;
        this.version = version;
    }

    public String getJson() {
        return json;
    }

    public Double getVersion() {
        return version;
    }


}
