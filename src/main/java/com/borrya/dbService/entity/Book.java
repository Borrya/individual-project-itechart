package com.borrya.dbService.entity;

public class Book {
    private int id;
    private String cover64;
    private byte[] cover;
    private String title;
    private String author;
    private String genre;
    private String publisher;
    private String date;
    private int pages;
    private long isbn;
    private String description;
    private int amount;
    private String status;

    public Book() {
    }

    public Book(int id) {
        this.id = id;
    }

    public Book(int id, String title, String author, String date, int amount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.amount = amount;
    }

    public Book(String title, String cover64, String author, String genre, String publisher, String date, long isbn, int pages, String description, int amount, String status) {
        this.title = title;
        this.cover64 = cover64;
        this.author = author;
        this.date = date;
        this.amount = amount;
        this.genre = genre;
        this.publisher = publisher;
        this.pages = pages;
        this.isbn = isbn;
        this.description = description;
        this.status = status;
    }

    public Book(String title, byte[] cover, String author, String genre, String publisher, String date, long isbn, int pages, String description, int amount, String status) {
        this.title = title;
        this.cover = cover;
        this.author = author;
        this.date = date;
        this.amount = amount;
        this.genre = genre;
        this.publisher = publisher;
        this.pages = pages;
        this.isbn = isbn;
        this.description = description;
        this.status = status;
    }

    public Book(int id, String title, String author, String genre, String publisher, String date, long isbn, int pages, String description, int amount) {
        this(id, title, author, date, amount);
        this.genre = genre;
        this.publisher = publisher;
        this.pages = pages;
        this.isbn = isbn;
        this.description = description;
    }

    public Book(int id, String cover, String title, String author, String genre, String publisher, String date, long isbn, int pages, String description, int amount) {
        this(id, title, author, date, amount);
        this.cover64 = cover;
        this.genre = genre;
        this.publisher = publisher;
        this.pages = pages;
        this.isbn = isbn;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCover64() {
        return cover64;
    }

    public void setCover64(String cover64) {
        this.cover64 = cover64;
    }

    public byte[] getCover() {
        return this.cover;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
