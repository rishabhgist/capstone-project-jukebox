package com.niit.jdp.services;

import java.sql.Connection;

public class DatabaseService {
    private static final String URL = "";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Connection connect() {
        return this.connection;
    }
}
