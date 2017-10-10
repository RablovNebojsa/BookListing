package com.example.rablov.nebojsa.booklisting;

/**
 * Created by Nebojsa on 10/9/2017.
 */

public class Book {

    private String title;
    private String authors;

    public Book(String title, String authors) {
        this.title = title;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
