package com.niit.jdp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
    private static final String URL = "jdbc:mysql://127.0.0.1:3307/jukebox";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private Connection connection;

    public DatabaseService() throws SQLException {
        connect();
    }

    public Connection getConnection() {
        return connection;
    }

    public boolean connect() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        return connection != null;
    }
}
