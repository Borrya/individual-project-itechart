package app.dbService.dao;

import app.dbService.entity.Book;
import app.dbService.executor.Executor;
import app.dbService.executor.ResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private final Executor executor;

    public BookDao(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void insertBook(Book book) throws SQLException {
        executor.execUpdate("INSERT INTO book (cover, title, authors, genres, publisher, publish_date, isbn, pages, description, total_amount, status) VALUES ('" + book.getCover64() + "' , '" + book.getTitle() + "', '" +
                book.getAuthor() + "', '" + book.getGenre() + "', '" + book.getPublisher() + "', '" + book.getDate() + "', " +
                book.getIsbn() + ", " + book.getPages() + ", '" + book.getDescription() + "', " + book.getAmount() + ", '" + book.getStatus() + "')");
    }

    @SuppressWarnings("unchecked")
    public List<Book> listAllBooks() throws SQLException {
        final List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";
        ResultHandler resultHandler = new ResultHandler() {
            @Override
            public Object handle(ResultSet resultSet) throws SQLException {
                resultSet.next();
                Book book = new Book(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(7), resultSet.getInt(11));
                listBook.add(book);
                return listBook;
            }
        };

        return (List<Book>) executor.execQuery(sql, resultHandler);
    }

    public void deleteBook(Book book) throws SQLException {
        String sql = "DELETE FROM book WHERE book_id = " + book.getId();
        executor.execUpdate(sql);
    }

    public void updateBook(Book book) throws SQLException {
        String sql = "UPDATE book SET title = '" + book.getTitle() + "', author = '" + book.getAuthor() + "', genres = '" + book.getGenre() + "', publisher = '" + book.getPublisher() +
                "', publish_date = '" + book.getDate() + "', isbn = " + book.getIsbn() + ", pages = " + book.getPages() + ", description = '" + book.getDescription() +
                "', total_amount = " + book.getAmount() + " WHERE book_id = " + book.getId();
        executor.execUpdate(sql);
    }

    public Book getBook(int id) throws SQLException {
        String sql = "SELECT * FROM book WHERE book_id = " + id;

        ResultHandler resultHandler = new ResultHandler() {
            @Override
            public Object handle(ResultSet resultSet) throws SQLException {
                resultSet.next();
                Book book = new Book(resultSet.getInt(1), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getLong(8), resultSet.getInt(9), resultSet.getString(10), resultSet.getInt(11));
                return book;
            }
        };

        return (Book) executor.execQuery(sql, resultHandler);
    }
}
