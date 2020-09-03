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

    /*public void insertUser(Book book) throws SQLException {
        executor.execUpdate("insert into users (login, password) values ('" + userProfile.getLogin() + "' , '" + userProfile.getPass() + "')");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }
     */
    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists book (id serial not null primary key, cover bytea, title varchar(150) not null," +
                " authors varchar(256) not null, genres varchar(256) not null, publisher varchar(256) not null, publish_date date not null, isbn bigint not null," +
                "pages integer not null, description text, total_amount integer, status varchar(50))");
    }

    public void insertBook(Book book) throws SQLException {
        executor.execUpdate("insert into books (cover, title, authors, genres, publisher, publish_date, isbn, pages, description, total_amount, status) values ('" + book.getCover() + "' , '" + book.getTitle() +
                book.getAuthor() + book.getGenre() + book.getPublisher() + book.getDate() + book.getIsbn() +
                book.getPages() + book.getIsbn() + book.getPages() + book.getDescription() + book.getAmount() + book.getStatus() + "')");
    }
}
