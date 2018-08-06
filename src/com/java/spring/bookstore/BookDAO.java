package com.java.spring.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * BookDAO.java
 * Esta clase DAO provee las opraciones CRUD para la tabla book.
 * @author Luis Moreyra
 *
 */
@Repository
public class BookDAO {

    public boolean insertBook(Book book) throws SQLException {
        Connection jdbcConnection = DBUtil.getConnection();

        String sql = "SELECT NVL(MAX(book_id), 0) + 1 book_id FROM book";
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            int book_id = resultSet.getInt("book_id");
            book.setId(book_id);
        }

        sql = "INSERT INTO book (book_id, title, author, price) VALUES (?, ?, ?, ?)";
        PreparedStatement statement2 = jdbcConnection.prepareStatement(sql);
        statement2.setInt(1, book.getId());
        statement2.setString(2, book.getTitle());
        statement2.setString(3, book.getAuthor());
        statement2.setFloat(4, book.getPrice());

        boolean rowInserted = statement2.executeUpdate() > 0;

        resultSet.close();
        statement.close();
        statement2.close();
        DBUtil.disconnect();
        return rowInserted;
    }

    public List<Book> listAllBooks() throws SQLException {
        Connection jdbcConnection = DBUtil.getConnection();

        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            Book book = new Book(id, title, author, price);
            listBook.add(book);
        }

        resultSet.close();
        statement.close();

        DBUtil.disconnect();

        return listBook;
    }

    public boolean deleteBook(Book book) throws SQLException {
        Connection jdbcConnection = DBUtil.getConnection();

        String sql = "DELETE FROM book where book_id = ?";

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, book.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowDeleted;
    }

    public boolean updateBook(Book book) throws SQLException {
        Connection jdbcConnection = DBUtil.getConnection();

        String sql = "UPDATE book SET title = ?, author = ?, price = ?";
        sql += " WHERE book_id = ?";

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setFloat(3, book.getPrice());
        statement.setInt(4, book.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        DBUtil.disconnect();
        return rowUpdated;
    }

    public Book getBook(int id) throws SQLException {
        Connection jdbcConnection = DBUtil.getConnection();

        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            float price = resultSet.getFloat("price");

            book = new Book(id, title, author, price);
        }

        resultSet.close();
        statement.close();
        DBUtil.disconnect();
        return book;
    }
}
