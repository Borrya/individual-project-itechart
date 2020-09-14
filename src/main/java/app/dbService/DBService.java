package app.dbService;

import app.dbService.dao.BookDAO;
import app.dbService.dataSets.Book;
import org.h2.jdbcx.JdbcDataSource;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        this.connection = getPostgresConnection();
    }

    public static Connection getPostgresConnection() {
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
            e.printStackTrace();
        }
        return null;
    }

    public void addBook(Book book) throws DBException{
        try {
            connection.setAutoCommit(false);
            BookDAO dao = new BookDAO(connection);
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
            } catch (SQLException ignore) {
            }
        }
    }
}

/*
CREATE DATABASE books;
CONNECT TO jdbc:postgresql://localhost:5432/books USER postgres IDENTIFIED BY 5325475;
GRANT ALL ON DATABASE books TO postgres;
 */
