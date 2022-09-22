package com.niit.jdp.repository;

import com.niit.jdp.exception.InsertErrorException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PlaylistRepository implements Repository<Playlist> {
    Playlist playlist;
    List<Playlist> playlistList;

    SongRepository songRepository;

    public PlaylistRepository() {
        this.playlistList = new ArrayList<>();
        this.songRepository = new SongRepository();
    }

    /**
     * This function returns a list of all the objects in the database.
     *
     * @param connection The connection to the database.
     * @return A list of objects of type T.
     */
    @Override
    public List<Playlist> displayAll(Connection connection) {
        String query = "SELECT * FROM `jukebox`.`playlist`";
        List<Song> songList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet playlistResult = preparedStatement.executeQuery();
            while (playlistResult.next()) {
                playlist = new Playlist();
                playlist.setId(playlistResult.getInt("playlist_id"));
                playlist.setName(playlistResult.getString("playlist_name"));
                String[] playlistData = playlistResult.getString("playlist_data").split(",");
                Song nextId = songRepository.displayAll(connection).iterator().next();
                for (String playlistDatum : playlistData) {
                    if (nextId.getId() == Integer.parseInt(playlistDatum)) {
                        songList.add(new Song(nextId.getId(), nextId.getName(), nextId.getGenre(), nextId.getLength(), nextId.getArtist(), nextId.getAlbum(), nextId.getPath()));
                    }
                }
                playlist.setSongList(songList);
                playlistList.add(playlist);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return playlistList;
    }

    /**
     * This function adds a new object to the database
     *
     * @param connection The connection to the database.
     * @param playlist   The object to be added to the database.
     * @return A boolean value.
     */
    @Override
    public boolean add(Connection connection, Playlist playlist) throws InsertErrorException {
        Map<Song, Integer> collect = playlist.getSongList().stream().collect(Collectors.toMap(Function.identity(), Song::getId));
        String listToStr = collect.values().toString().replaceAll("\\[", "").replaceAll("]", "").replace(" ", "");
        String insertQuery = "insert into `jukebox`.`playlist` (`playlist_name`, `playlist_data`) VALUES (?, ?)";
        int numberOfRowsAffected;
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, playlist.getName());
            preparedStatement.setString(2, listToStr);
            numberOfRowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new InsertErrorException(e.getMessage());
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
    public boolean delete(Connection connection, int id) throws SQLException {
        String deleteQuery = "DELETE FROM `jukebox`.`playlist` WHERE(`playlist_id` = ?)";
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
     * @param playlists The list of objects to be sorted.
     * @return A list of objects that are sorted by alphabetical order.
     */
    @Override
    public List<Playlist> sortByAlphabet(List<Playlist> playlists) {
        return playlists.stream().sorted((name1, name2) -> String.CASE_INSENSITIVE_ORDER.compare(name1.getName(), name2.getName())).toList();
    }
}
