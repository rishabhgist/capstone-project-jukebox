package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    Song song;

    public List<Song> displayAllSong(Connection connection) {
        List<Song> songsList = new ArrayList<>();
        String query = "SELECT * FROM `jukebox`.`song`";
        try (Statement statement = connection.createStatement()) {
            ResultSet songResult = statement.executeQuery(query);
            while (songResult.next()) {
                song = new Song();
                song.setName(songResult.getString("song_name"));
                song.setArtist(songResult.getString("song_artist"));
                song.setAlbum(songResult.getString("song_album"));
                song.setGenre(songResult.getString("song_genre"));
                song.setPath(songResult.getString("song_url"));
                song.setLength(songResult.getDouble("song_length"));
                songsList.add(song);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return songsList;
    }

    public void displaySongId(Connection connection, int playlistId) {
    }

    public boolean addSongToLibray(Connection connection, int songId, int playlistId) {
        return false;
    }

    public boolean addNewSong(Connection connection, String name, String artist, String genre, double duration, String filename) {
        return false;
    }

    public boolean addNewSong(Connection connection, int songId) {
        return false;
    }

}
