package app.dbService;

import app.dbService.entity.Book;

public interface BookService {

    void addBook(Book book) throws DBException;
}
