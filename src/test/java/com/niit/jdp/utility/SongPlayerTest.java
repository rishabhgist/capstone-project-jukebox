package com.niit.jdp.utility;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SongPlayerTest {
    SongPlayer songPlayer;

    @BeforeEach
    void setUp() {
        songPlayer = new SongPlayer();
    }

    @AfterEach
    void tearDown() {
        songPlayer = null;
    }

    @Test
    void givenCorrectFileCheckPlayerStatus() {
        songPlayer.setFilePath("src/test/resources/lie-2-you-ft-dylan-emmet.wav");
        songPlayer.play();
        Assertions.assertFalse(songPlayer.isClipStatus());
    }

    @Test
    void givenIncorrectFilePathPlayerStatus() {
        songPlayer.setFilePath("null");
        Assertions.assertThrows(RuntimeException.class, () -> songPlayer.play());
    }
}