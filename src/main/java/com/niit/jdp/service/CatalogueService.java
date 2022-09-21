package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CatalogueService {
    SongRepository songRepository;
    DatabaseService databaseService;
    PlaylistRepository playlistRepository;

    public CatalogueService() throws SQLException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
        playlistRepository = new PlaylistRepository();
    }

    public void printDefault() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            displayHeader();
            System.out.println("1.View Songs List\n2.View Playlists\n3.Sort song by alphabet\n4.Find a song\n5.Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    List<Song> songList = songRepository.displayAllSong(databaseService.getConnection());
                    displayHeader();
                    System.out.println("Sr No. Song Title          Artist          Genre       Duration    Album       ");
                    songList.stream().map(Song::toString).forEach(System.out::println);
                    break;
                case 2:
                    List<Playlist> playlistLists = playlistRepository.displayAllPlaylist(databaseService.getConnection());
                    displayHeader();
                    System.out.println("Sr No.     Playlist  ");
                    playlistLists.stream().map(Playlist::toString).forEach(System.out::println);
                case 3:
                    sortByAlphabet();
                    break;
                case 4:
                    findSong();
                    break;
                case 5:
                    System.out.println("Thanks for using");
                    break;
                default:
                    System.err.println("Invalid choice");
            }
        } while (choice != 5);
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
