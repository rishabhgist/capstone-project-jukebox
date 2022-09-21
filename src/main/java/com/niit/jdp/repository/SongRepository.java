package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements Repository<Song> {
    Song song;

    List<Song> songsList;

    public SongRepository() {
        this.songsList = new ArrayList<>();
    }


    /**
     * This function returns a list of all the objects in the database.
     *
     * @param connection The connection to the database.
     * @return A list of objects of type Song.
     */
    @Override
    public List<Song> displayAll(Connection connection) {
        String query = "SELECT * FROM `jukebox`.`song`";
        try (Statement statement = connection.createStatement()) {
            ResultSet songResult = statement.executeQuery(query);
            while (songResult.next()) {
                song = new Song();
                song.setId(songResult.getInt("song_id"));
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

    /**
     * Given a connection to a database, return the object with the given id.
     *
     * @param connection The connection to the database.
     * @param id         The id of the display you want to get.
     * @return A single row from the table.
     */
    @Override
    public Song displayById(Connection connection, int id) {
        return null;
    }

    /**
     * This function adds a new object to the database
     *
     * @param connection The connection to the database.
     * @param song       The object to be added to the database.
     * @return A boolean value.
     */
    @Override
    public boolean add(Connection connection, Song song) {
        return false;
    }

    /**
     * Delete the row with the given id from the table.
     *
     * @param connection The connection to the database.
     * @param id         The id of the record to delete.
     * @return A boolean value.
     */
    @Override
    public boolean delete(Connection connection, int id) {
        return false;
    }
}
