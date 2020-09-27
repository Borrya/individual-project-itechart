package app.dbService;

import app.dbService.dao.BookDao;
import app.dbService.entity.Book;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class PostgresBookService implements BookService {
    private final static Connection connection = getPostgresConnection();
    private BookDao dao;

    public PostgresBookService() {
        dao = new BookDao(connection);
    }

    private static Connection getPostgresConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());
                String url = "jdbc:postgresql://localhost:5432/books";
                String name = "postgres";
                String pass = "5325475lol";

                JdbcDataSource ds = new JdbcDataSource();
                ds.setURL(url);
                ds.setUser(name);
                ds.setPassword(pass);

                Connection connection = DriverManager.getConnection(url, name, pass);
                return connection;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            Logger.getGlobal().info("Connection failed.");
        }
        return null;
    }

    protected void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void addBook(Book book) throws DBException {
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
            dao.insertBook(book);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(Book book) throws DBException {
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
            dao.deleteBook(book);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) throws DBException {

        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
            dao.updateBook(book);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Book> listBooks() throws DBException {
        List<Book> listBook = null;
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
            listBook = dao.listAllBooks();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBook;
    }

    @Override
    public Book getBook(int id) throws DBException {
        Book book = null;
        try {
            if (connection != null) {
                connection.setAutoCommit(false);
            }
            book = dao.getBook(id);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
