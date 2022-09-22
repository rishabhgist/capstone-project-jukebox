package com.niit.jdp.service;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
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


    public CatalogueService() throws SQLException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
        playlistRepository = new PlaylistRepository();
        songList = new ArrayList<>();
    }

    public void printDefault() {
        Scanner input = new Scanner(System.in);
        int choice;
        displayHeader();
        do {
            songList = songRepository.displayAll(databaseService.getConnection());
            System.out.println("\nPlease select from below options or enter 5 to exit");
            System.out.println("1.View All Songs \n2.View Playlists\n3.View song by alphabet\n4.Find a song by name\n5.Exit");
            choice = input.nextInt();
            String songFormat = "%-7s %-20s %-16s %-10s %-10s %-1s";
            String playlistFormat = "%-10s %-15s %-1s";

            switch (choice) {
                case 1 -> {
                    System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album");
                    System.out.println();
                    for (Song val : songList) {
                        System.out.format(songFormat, val.getId(), val.getName(), val.getArtist(), val.getGenre(), val.getLength(), val.getAlbum() + "\n");
                    }
                    setSongListNull();
                }
                case 2 -> {
                    List<Playlist> playlistLists = playlistRepository.displayAll(databaseService.getConnection());
                    System.out.format(playlistFormat, "Sr. No", "Playlist Name", "Total Songs\n");
                    for (Playlist playlistList : playlistLists) {
                        System.out.format(playlistFormat, playlistList.getId(), playlistList.getName(), playlistList.getSongList().size() + "\n");
                    }
                    setSongListNull();
                }
                case 3 -> {
                    List<Song> sortedSong = songRepository.sortByAlphabet(songList);
                    System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album" + "\n");
                    for (Song val : sortedSong) {
                        System.out.format(songFormat, val.getId(), val.getName(), val.getArtist(), val.getGenre(), val.getLength(), val.getAlbum() + "\n");
                    }
                    setSongListNull();
                }
                case 4 -> {
                    System.out.println("Enter song name to find");
                    System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album" + "\n");
                    song = songRepository.displayByName(databaseService.getConnection(), "Night");
                    System.out.format(songFormat, song.getId(), song.getName(), song.getArtist(), song.getGenre(), song.getLength(), song.getAlbum() + "\n");
                    setSongListNull();
                }
                case 5 -> System.out.println("Thanks for using");
                default -> System.err.println("Invalid choice");
            }
        } while (choice != 5);
    }

    public void playSong(Song song) {
        System.out.println();
    }

    public void setSongListNull() {
        this.songList = null;
    }

    public void displayHeader() {
        System.out.println("===============================================================================");
        System.out.println("||                               The Jukebox                                 ||");
        System.out.println("===============================================================================");
    }
}