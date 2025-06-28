package com.library.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String description;

    public Book() {}
    public Book(String title, String author, String isbn, String description) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.description = description;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return String.format("[%d] %s by %s (ISBN: %s)", id, title, author, isbn);
    }
}
