package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatalogueRepository {
    Song song;

    public List<Song> displayCatalogue(Connection connection) {
        List<Song> songsList = new ArrayList<>();
        String query = "SELECT * FROM `jukebox`.`song`";
        try {
            Statement statement = connection.createStatement();
            ResultSet songResult = statement.executeQuery(query);
            while (songResult.next()) {
                song = new Song();
                song.setSongName(songResult.getString("song_name"));
                song.setSongArtist(songResult.getString("song_artist"));
                song.setSongAlbum(songResult.getString("song_album"));
                song.setSongGenre(songResult.getString("song_genre"));
                song.setSongUrl(songResult.getString("song_url"));
                song.setSongLength(songResult.getDouble("song_length"));
                songsList.add(song);
            }
        } catch (SQLException e) {
            System.out.println("Incorrect search query" + e.getMessage());
        }
        return songsList;
    }

    public void printCatalogue(List<Song> songList) {

    }

    public List<Song> sortByAlphabet() {
        return null;
    }

    public List<Song> findSong() {
        return null;
    }

    public void playSong() {
    }
}
