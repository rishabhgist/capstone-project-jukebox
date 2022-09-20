package com.niit.jdp.services;

import java.sql.Connection;

public class DatabaseService {
    private final String URL = "";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Connection connect() {
        return this.connection;
    }
}
