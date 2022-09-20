package com.niit.jdp.repository;

import java.sql.Connection;

public class PlaylistRepository {
    // display all playlist
    public void displayAllPlaylist(Connection connection) {
    }

    //display playlist by id
    public void displayPlaylistById(Connection connection, int playlistId) {
    }

    public boolean createPlaylist(Connection connection, String playlistName) {
        return false;
    }

    public boolean editPlaylistName(Connection connection, String newPlaylistName, int playlistId) {
        return false;
    }

    public boolean deletePlaylist(Connection connection, int playlistId) {
        return false;
    }

}
