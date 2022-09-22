package com.niit.jdp.repository;

import com.niit.jdp.exception.InsertErrorException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PlaylistRepositoryTest {
    PlaylistRepository playlistRepository;
    DatabaseService databaseService;

    Playlist playlist;

    @BeforeEach
    void setUp() throws SQLException {
        databaseService = new DatabaseService();
        playlistRepository = new PlaylistRepository();
    }

    @AfterEach
    void tearDown() {
        playlistRepository = null;
        databaseService = null;
    }

    @Test
    void givenCorrectIdDeleteSuccessFull() throws SQLException {
        Assertions.assertTrue(playlistRepository.delete(databaseService.getConnection(), 8));
    }

    @Test
    void givenIncorrectIdDeletionFail() throws SQLException {
        assertFalse(playlistRepository.delete(databaseService.getConnection(), 0));
    }

    @Test
    void givenNullPlaylistAddFails() {
        Assertions.assertThrows(InsertErrorException.class, () -> playlistRepository.add(databaseService.getConnection(), playlist));
    }
}