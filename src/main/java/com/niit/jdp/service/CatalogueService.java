package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongComparator;
import com.niit.jdp.repository.SongRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CatalogueService {
    Song song;
    SongRepository songRepository;
    DatabaseService databaseService;
    PlaylistRepository playlistRepository;

    List<Song> songList;
    SongComparator songComparator;


    public CatalogueService() throws SQLException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
        playlistRepository = new PlaylistRepository();
        songList = new ArrayList<>();
        songComparator = new SongComparator();
    }

    public void printDefault() {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            displayHeader();
            songList = songRepository.displayAll(databaseService.getConnection());
            System.out.println("1.View Songs List\n2.View Playlists\n3.Sort song by alphabet\n4.Find a song\n5.Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.format("%-7s %-20s %-16s %-10s %-10s %-1s", "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album");
                    System.out.println();
                    for (Song song : songList) {
                        System.out.format("%-7d %-20s %-16s %-10s %-10s %-1s", song.getId(), song.getName(), song.getArtist(), song.getGenre(), song.getLength(), song.getAlbum() + "\n");
                    }
                }
                case 2 -> {
                    List<Playlist> playlistLists = playlistRepository.displayAll(databaseService.getConnection());
                    System.out.println("Sr No.     Playlist  ");
                    playlistLists.stream().map(Playlist::toString).forEach(System.out::println);
                }
                case 3 -> {
                    List<Song> sortedSong = songRepository.sortByAlphabet(songList, songComparator);
                    System.out.format("%-7s %-20s %-16s %-10s %-10s %-1s", "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album" + "\n");
                    for (Song song : sortedSong) {
                        System.out.format("%-7d %-20s %-16s %-10s %-10s %-1s", song.getId(), song.getName(), song.getArtist(), song.getGenre(), song.getLength(), song.getAlbum() + "\n");
                    }
                }
                case 4 -> {
                    System.out.println("Enter song name to find");
                    System.out.format("%-7s %-20s %-16s %-10s %-10s %-1s", "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album" + "\n");
                    song = songRepository.displayByName(databaseService.getConnection(), "Night");
                    System.out.format("%-7d %-20s %-16s %-10s %-10s %-1s", song.getId(), song.getName(), song.getArtist(), song.getGenre(), song.getLength(), song.getAlbum() + "\n");
                }
                case 5 -> System.out.println("Thanks for using");
                default -> System.err.println("Invalid choice");
            }
        } while (choice != 5);
    }

    public void playSong() {
    }

    public void displayHeader() {
        System.out.println("===============================================================================");
        System.out.println("||                               The Jukebox                                 ||");
        System.out.println("===============================================================================");
    }
}
