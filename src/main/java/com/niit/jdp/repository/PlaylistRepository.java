package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Stream;

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

    /**
     * Sort a list of objects by alphabetical order.
     *
     * @param playlists The list of objects to be sorted.
     * @return A list of objects that are sorted by alphabetical order.
     */
    @Override
    public List<Playlist> sortByAlphabet(List<Playlist> playlists, SongComparator songComparator) {
        Stream<Playlist> sorted = playlists.stream().sorted((name1, name2) -> String.CASE_INSENSITIVE_ORDER.compare(name1.getName(), name2.getName()));
        sorted.forEach(System.out::println);
        return playlists;
    }

    public boolean editPlaylistName(Connection connection, String newPlaylistName, int playlistId) {
        return false;
    }
}
