package app.dbService;

import app.dbService.dao.BookDao;
import app.dbService.entity.Book;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PostgresBookService implements BookService {
    private final Connection connection;
    private BookDao dao;

    public PostgresBookService() {
        this.connection = getPostgresConnection();
        dao = new BookDao(connection);
    }

    private static Connection getPostgresConnection() {
        try {
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
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            Logger.getGlobal().info("Connection failed.");
        }
        return null;
    }

    @Override
    public void addBook(Book book) throws DBException {
        try {
            connection.setAutoCommit(false);
            dao.insertBook(book);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {
            }
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ignore) {
            }
        }
    }
}
