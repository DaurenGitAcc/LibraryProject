package com.absat.library.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Book name should not be empty")
    @Size(min = 5,max = 150,message = "Length of book name should be greater than 5")
    private String name;
    @NotEmpty(message = "Author name should not be empty")
    @Size(min = 5,max = 150,message = "Length of author name should be greater than 5")
    private String author;
    private int year;
    private int currentReader;

    public Book(){}
    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getCurrentReader() {
        return currentReader;
    }

    public void setCurrentReader(int currentReader) {
        this.currentReader = currentReader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
