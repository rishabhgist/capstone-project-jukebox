package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlaylistRepository implements Repository<Playlist> {
    Playlist playlist;
    List<Playlist> playlistList;

    /**
     * This function returns a list of all the objects in the database.
     *
     * @param connection The connection to the database.
     * @return A list of objects of type T.
     */
    @Override
    public List<Playlist> displayAll(Connection connection) {
        String query = "SELECT * FROM `jukebox`.`playlist`";
        try (Statement statement = connection.createStatement()) {
            ResultSet songResult = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playlistList;
    }

    /**
     * @param connection The connection to the database.
     * @param name
     * @return
     */
    @Override
    public Playlist displayByName(Connection connection, String name) {
        return null;
    }

    /**
     * Given a connection to a database, return the object with the given id.
     *
     * @param connection The connection to the database.
     * @param id         The id of the display you want to get.
     * @return A single row from the table.
     */

    /**
     * This function adds a new object to the database
     *
     * @param connection The connection to the database.
     * @param playlist   The object to be added to the database.
     * @return A boolean value.
     */
    @Override
    public boolean add(Connection connection, Playlist playlist) {
        Map<Song, Integer> collect = playlist.getSongList().stream().collect(Collectors.toMap(Function.identity(), Song::getId));
        String listToStr = collect.values().toString();
        String insertQuery = "insert into `jukebox`.`playlist` (`playlist_id`, `playlist_name`, `playlist_data`) VALUES (?, ?, ?)";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, playlist.getId());
            preparedStatement.setString(2, playlist.getName());
            preparedStatement.setString(3, listToStr);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return numberOfRowsAffected > 0;
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

    /**
     * Sort a list of objects by alphabetical order.
     *
     * @param playlists The list of objects to be sorted.
     * @return A list of objects that are sorted by alphabetical order.
     */
    @Override
    public List<Playlist> sortByAlphabet(List<Playlist> playlists) {
        return playlists.stream().sorted((name1, name2) -> String.CASE_INSENSITIVE_ORDER.compare(name1.getName(), name2.getName())).toList();
    }

    public boolean editPlaylistName(Connection connection, String newPlaylistName, int playlistId) {
        return false;
    }
}
