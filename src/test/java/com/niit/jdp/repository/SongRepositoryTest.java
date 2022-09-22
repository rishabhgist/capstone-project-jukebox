package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class SongRepositoryTest {
    DatabaseService databaseService;
    SongRepository songRepository;
    Song song;

    @BeforeEach
    void setUp() {
        try {
            databaseService = new DatabaseService();
            songRepository = new SongRepository();
            song = new Song();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        databaseService = null;
        songRepository = null;
        song = new Song();
    }

    @Test
    void givenCorrectNameSuccessful() {
        song = songRepository.displayByName(databaseService.getConnection(), "Lie");
        Assertions.assertEquals("Lie 2 You", song.getName());
    }

    @Test
    void givenIncorrectNameFailed() {
        song = songRepository.displayByName(databaseService.getConnection(), "0");
        Assertions.assertNull(song);
    }

    @Test
    void givenIncorrectIdDeleteFails() throws SQLException {
        Assertions.assertFalse(songRepository.delete(databaseService.getConnection(), 0));
    }
}