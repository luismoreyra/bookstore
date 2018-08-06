package com.java.spring.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DBUtil {

    final private static String jdbcURL = "jdbc:oracle:thin:@localhost:1521:xe";
    final private static String jdbcUsername = "bookstore";
    final private static String jdbcPassword = "bookstore";
    private static Connection jdbcConnection;

    protected static Connection getConnection() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("oracle.jdbc.OracleDriver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
        return jdbcConnection;
    }

    protected static void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
