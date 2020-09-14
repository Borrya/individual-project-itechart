package app.dbService.dao;

import app.dbService.dataSets.Book;
import app.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class BookDAO {
    private Executor executor;

    public BookDAO(Connection connection){
        this.executor = new Executor(connection);
    }

    public void insertBook(Book book) throws SQLException {
        executor.execUpdate("insert into book (cover, title, authors, genres, publisher, publish_date, isbn, pages, description, total_amount, status) values ('" + book.getCover64() + "' , '" + book.getTitle()+ "', '" +
                book.getAuthor() + "', '" + book.getGenre() + "', '" + book.getPublisher() + "', '" + book.getDate()+ "', " +
                book.getIsbn()+ ", " + book.getPages() + ", '" + book.getDescription() + "', " + book.getAmount() + ", '" + book.getStatus() + "')");
    }
}
