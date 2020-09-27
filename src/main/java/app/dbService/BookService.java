package app.dbService;

import app.dbService.entity.Book;

import java.util.List;

public interface BookService {

    void addBook(Book book) throws DBException;

    void deleteBook(Book book) throws DBException;

    void updateBook(Book book) throws DBException;

    List<Book> listBooks() throws DBException;

    Book getBook(int id) throws DBException;
}
