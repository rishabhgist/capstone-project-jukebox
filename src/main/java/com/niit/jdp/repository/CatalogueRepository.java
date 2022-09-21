package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogueRepository {
    Song song;
    DatabaseService databaseService;

    public List<Song> fetchSongs() {
        List<Song> songsList = new ArrayList<>();
        String query = "SELECT * FROM `jukebox`.`song`";
        try {
            Statement statement = databaseService.getConnection().createStatement();
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

    public void printMusicMenu(List<Song> songList) {
        int serial = 1;
        System.out.println("Sr No. Song Title          Artist          Genre       Duration    Album       ");
        for (Song fetchSong : songList) {
            System.out.println(serial + fetchSong.toString());
            serial++;
        }
    }

    public void printDefault() {
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        do {
            displayHeader();
            System.out.println("1. View Songs List\n2.View Playlists\n3.Sort song by alphabet\n4.Find a song");
            switch (choice) {
                case 1:
                    printMusicMenu(fetchSongs());
                    break;
                case 2:
                    break;
                case 3:
                    sortByAlphabet();
                    break;
                case 4:
                    findSong();
                    break;
                case 5:
                    System.out.println("Enter 5 to exit");
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        } while (choice == 5);
    }

    public List<Song> sortByAlphabet() {
        return null;
    }

    public List<Song> findSong() {
        return null;
    }

    public void playSong() {
    }

    public void displayHeader() {
        System.out.println("===============================================================================");
        System.out.println("=                               The Jukebox                                   =");
        System.out.println("===============================================================================");
    }
}
