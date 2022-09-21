package com.niit.jdp;

import com.niit.jdp.service.DatabaseService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseService databaseService = new DatabaseService();
        } catch (SQLException e) {
            System.err.println("Unable to connect to Database, " + e.getMessage());
        }

    }
}