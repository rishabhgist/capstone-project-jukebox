package com.niit.jdp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseServiceTest {
    DatabaseService databaseService;

    @BeforeEach
    void setUp() {
        try {
            databaseService = new DatabaseService();
        } catch (SQLException e) {
            System.err.println("Access denied");
        }
    }

    @AfterEach
    void tearDown() {
        databaseService = null;
    }

    @Test
    void givenUrlUsernamePasswordTestConnectionSuccessful() throws SQLException {
        Assertions.assertTrue(databaseService.connect());
    }

    @Test
    void givenIncorrectValueTestConnectionFail() {
        Assertions.assertThrows(SQLException.class, () -> DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/jukebox", "root", ""));
    }
}