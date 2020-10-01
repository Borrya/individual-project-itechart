package app.dbService;

import app.dbService.dao.BookDao;
import app.dbService.entity.Book;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class PostgresBookService implements BookService {

    public PostgresBookService() {
    }

    public static Connection getPostgresConnection() {
        try {
            Connection connection = null;
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
            String url = "jdbc:postgresql://localhost:5432/books";
            String name = "postgres";
            String pass = "5325475lol";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            Logger.getGlobal().info("Connection failed.");
        }
        return null;
    }

    protected void disconnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void addBook(Book book) throws DBException {
        try {
            Connection connection = getPostgresConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            } else {
                throw new DBException(new NullPointerException());
            }
            BookDao dao = new BookDao(connection);
            dao.insertBook(book);
            connection.commit();
            connection.setAutoCommit(true);
            disconnect(connection);
        } catch (SQLException e) {
            Logger.getGlobal().info("Connection for adding failed.");
        }
    }

    @Override
    public void deleteBook(Book book) throws DBException {
        try {
            Connection connection = getPostgresConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            } else {
                throw new DBException(new NullPointerException());
            }
            BookDao dao = new BookDao(connection);
            dao.deleteBook(book);
            connection.commit();
            disconnect(connection);
        } catch (SQLException e) {
            Logger.getGlobal().info("Connection for deleting failed.");
        }
    }

    @Override
    public void updateBook(Book book) throws DBException {

        try {
            Connection connection = getPostgresConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            } else {
                throw new DBException(new NullPointerException());
            }
            BookDao dao = new BookDao(connection);
            dao.updateBook(book);
            connection.commit();
            disconnect(connection);
        } catch (SQLException e) {
            Logger.getGlobal().info("Connection for updating failed.");
        }

    }

    @Override
    public List<Book> listBooks() throws DBException {
        List<Book> listBook = null;
        try {
            Connection connection = getPostgresConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            } else {
                throw new DBException(new NullPointerException());
            }
            BookDao dao = new BookDao(connection);
            listBook = dao.listAllBooks();
            connection.commit();
            disconnect(connection);
            return listBook;
        } catch (SQLException e) {
            Logger.getGlobal().info("Connection for list of books failed.");
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Book> getBook(int id) throws DBException {
        Optional<Book> book = null;
        try {
            Connection connection = getPostgresConnection();
            if (connection != null) {
                connection.setAutoCommit(false);
            } else {
                throw new DBException(new NullPointerException());
            }
            BookDao dao = new BookDao(connection);
            book = Optional.of(dao.getBook(id));
            connection.commit();
        } catch (SQLException e) {
            Logger.getGlobal().info("Connection for getting book failed.");
        }
        return book;
    }
}
