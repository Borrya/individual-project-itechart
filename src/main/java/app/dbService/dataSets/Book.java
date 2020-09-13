package app.dbService.dataSets;

import java.io.File;

public class Book {
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

    public Book(){
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getDate() {
        return date;
    }

    public int getPages() {
        return pages;
    }

    public long getIsbn() {
        return isbn;
    }

    public int getAmount() {
        return amount;
    }

    public String getCover64() {
        return cover64;
    }

    public byte[] getCover(){ return this.cover;}

    public String getStatus(){ return status; }

    public String getDescription() { return description; }

    public void setCover64(String cover64) {
        this.cover64 = cover64;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
