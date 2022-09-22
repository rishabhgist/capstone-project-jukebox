package com.niit.jdp.repository;

import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PlaylistRepositoryTest {
    PlaylistRepository playlistRepository;
    DatabaseService databaseService;

    @BeforeEach
    void setUp() {
        try {
            databaseService = new DatabaseService();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        playlistRepository = new PlaylistRepository();

    }

    @AfterEach
    void tearDown() {
        playlistRepository = null;
        databaseService = null;
    }

    @Test
    void givenIncorrectIdDeletionFail() throws SQLException {
        assertFalse(playlistRepository.delete(databaseService.getConnection(), 0));
    }
}