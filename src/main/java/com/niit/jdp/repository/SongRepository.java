package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.sql.*;
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
            return songsList;
        }
        return songsList;
    }

    /**
     * Given a connection to a database, return the object with the given id.
     *
     * @param connection The connection to the database.
     * @param name       The name of the song you want to get.
     * @return A single row from the table.
     */
    public Song displayByName(Connection connection, String name) {
        String query = "SELECT * FROM `jukebox`.`song` WHERE(`song_name` LIKE ?)";
        try (PreparedStatement readValues = connection.prepareStatement(query)) {
            readValues.setString(1, name + "%");
            ResultSet songResult = readValues.executeQuery();
            //noinspection LoopStatementThatDoesntLoop
            while (songResult.next()) {
                song = new Song();
                song.setId(songResult.getInt("song_id"));
                song.setName(songResult.getString("song_name"));
                song.setArtist(songResult.getString("song_artist"));
                song.setAlbum(songResult.getString("song_album"));
                song.setGenre(songResult.getString("song_genre"));
                song.setPath(songResult.getString("song_url"));
                song.setLength(songResult.getDouble("song_length"));
                return song;
            }

        } catch (SQLException | NullPointerException e) {
            System.err.println("No Song found");
        }
        return song;
    }

    /**
     * This function adds a new object to the database
     *
     * @param connection The connection to the database.
     * @param song       The object to be added to the database.
     * @return A boolean value.
     */
    @Override
    public boolean add(Connection connection, Song song) throws SQLException {
        String addQuery = "insert into `jukebox`.`song` (`song_name`, `song_genre`, `song_artist`, `song_length`, `song_album`, `song_url`) " + "VALUES (?, ?, ?, ?, ?, ?)";
        int noOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(addQuery)) {
            preparedStatement.setString(1, song.getName());
            preparedStatement.setString(2, song.getGenre());
            preparedStatement.setString(3, song.getArtist());
            preparedStatement.setDouble(4, song.getLength());
            preparedStatement.setString(5, song.getAlbum());
            preparedStatement.setString(6, song.getPath());
            noOfRowsAffected = preparedStatement.executeUpdate();
        }
        return noOfRowsAffected > 0;
    }

    /**
     * Delete the row with the given id from the table.
     *
     * @param connection The connection to the database.
     * @param id         The id of the record to delete.
     * @return A boolean value.
     */
    @Override
    public boolean delete(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM `jukebox`.`song` WHERE(`song_id` = ?)";
        int noOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, id);
            noOfRowsAffected = preparedStatement.executeUpdate();
        }
        return noOfRowsAffected > 0;
    }

    /**
     * Sort a list of objects by alphabetical order.
     *
     * @param songList The list of objects to be sorted.
     * @return A list of objects that are sorted by alphabetical order.
     */
    @Override
    public List<Song> sortByAlphabet(List<Song> songList) {
        return songList.stream().sorted((name1, name2) -> String.CASE_INSENSITIVE_ORDER.compare(name1.getName(), name2.getName())).toList();
    }
}
