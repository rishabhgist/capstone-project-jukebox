package com.niit.jdp.repository;

import java.sql.Connection;

public class SongRepository {
    public void displayAllSong(Connection connection) {
    }

    public void displaySong(Connection connection, int playlistId) {
    }

    public boolean addSongToPlaylist(Connection connection, int songId, int playlistId) {
        return false;
    }

    public boolean addNewSong(Connection connection, String name, String artist, String genre, double duration, String filename) {
        return false;
    }

    public boolean addNewSong(Connection connection, int songId) {
        return false;
    }

}
