package com.borrya.dbService;

import com.borrya.dbService.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void addBook(Book book) throws DBException;

    void deleteBook(Book book) throws DBException;

    void updateBook(Book book) throws DBException;

    List<Book> listBooks() throws DBException;

    Optional<Book> getBook(int id) throws DBException;
}
