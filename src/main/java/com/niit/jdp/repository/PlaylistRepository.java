package com.niit.jdp.repository;

import com.niit.jdp.model.Playlist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
     * Given a connection to a database, return the object with the given id.
     *
     * @param connection The connection to the database.
     * @param id         The id of the display you want to get.
     * @return A single row from the table.
     */
    @Override
    public Playlist displayById(Connection connection, int id) {
        String query = "SELECT * FROM `jukebox`.`playlist`";

        return null;
    }

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

    public boolean editPlaylistName(Connection connection, String newPlaylistName, int playlistId) {
        return false;
    }
}
