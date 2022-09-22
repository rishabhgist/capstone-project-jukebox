package com.niit.jdp.service;

import com.niit.jdp.exception.InsertErrorException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.utility.SongPlayer;

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
    SongPlayer songPlayer;


    public CatalogueService() throws SQLException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
        playlistRepository = new PlaylistRepository();
        songPlayer = new SongPlayer();
        songList = new ArrayList<>();
    }

    public void printDefault() throws SQLException {
        Scanner input = new Scanner(System.in);
        int choice;
        displayHeader();
        do {
            songList = songRepository.displayAll(databaseService.getConnection());
            System.out.println("\nPlease select from below options or enter 0 to exit");
            System.out.println("1.Song\n2.Playlists\n0.Exit");
            choice = input.nextInt();
            String playlistFormat = "%-10s %-15s %-1s";

            switch (choice) {
                case 1 -> songCatalogue();
                case 2 -> {
                    List<Playlist> playlistLists = playlistRepository.displayAll(databaseService.getConnection());
                    System.out.format(playlistFormat, "Sr. No", "Playlist Name", "Total Songs\n");
                    for (Playlist playlistList : playlistLists) {
                        System.out.format(playlistFormat, playlistList.getId(), playlistList.getName(), playlistList.getSongList().size() + "\n");
                    }
                }
                case 5 -> System.out.println("Thanks for using");
                default -> System.err.println("Invalid choice");
            }
        } while (choice != 0);
    }

    public void songCatalogue() throws SQLException {
        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nPlease select from below options or enter 0 to go back");
            System.out.println("1.View All Songs\n2.Add Song\n3.Delete Song\n4.Find song by name\n0.Exit");
            choice = input.nextInt();
            String songFormat = "%-7s %-20s %-16s %-10s %-10s %-1s";
            switch (choice) {
                case 1 -> {
                    System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album\n");
                    songList.forEach(val -> System.out.format(songFormat, val.getId(), val.getName(), val.getArtist(), val.getGenre(), val.getLength(), val.getAlbum() + "\n"));
                    System.out.println("Enter the song id you want to play");
                    int songToPlay = input.nextInt();
                    playSong(songToPlay);
                }
                case 2 -> {
                    if (getInputFromUserAndAdd()) System.out.println("Song Added Successfully");
                    else System.err.println("Song was not added");
                }
                case 3 -> {
                    int songId = input.nextInt();
                    if (songRepository.delete(databaseService.getConnection(), songId))
                        System.out.println("Deleted Successful");
                    else System.err.println("Unable to delete song, incorrect song id");
                }
                case 4 -> {
                    System.out.println("Enter song name to find");
                    System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album" + "\n");
                    String findName = input.nextLine();
                    song = songRepository.displayByName(databaseService.getConnection(), findName);
                    System.out.format(songFormat, song.getId(), song.getName(), song.getArtist(), song.getGenre(), song.getLength(), song.getAlbum() + "\n");
                }
                case 5 -> {
                    List<Song> sortedSong = songRepository.sortByAlphabet(songList);
                    System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album" + "\n");
                    for (Song val : sortedSong) {
                        System.out.format(songFormat, val.getId(), val.getName(), val.getArtist(), val.getGenre(), val.getLength(), val.getAlbum() + "\n");
                    }
                }
                default -> System.err.println("Invalid Choice");
            }

        } while (choice != 0);
    }

    public void playSong(int songId) throws SQLException {
        for (Song songObj : songList) {
            if (songObj.getId() == songId) {
                songPlayer.setFilePath(songObj.getPath());
                if (songPlayer.isClipStatus()) {
                    songPlayer.stop();
                } else {
                    songPlayer.setClipStatus(true);
                    songPlayer.play();
                }
            } else {
                System.err.println("Song not found");
            }
        }
    }

    public void displayHeader() {
        System.out.println("===============================================================================");
        System.out.println("||                               The Jukebox                                 ||");
        System.out.println("===============================================================================");
    }

    public boolean getInputFromUserAndAdd() {
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Enter Song name");
            String name = input.nextLine();
            System.out.println("Enter Genre");
            String genre = input.nextLine();
            System.out.println("Enter Artist");
            String artist = input.nextLine();
            System.out.println("Enter Length");
            double length = input.nextDouble();
            System.out.println("Enter Album Name");
            String album = input.nextLine();
            System.out.println("Enter Url");
            String url = input.nextLine();
            return songRepository.add(databaseService.getConnection(), new Song(name, genre, length, artist, album, url));
        } catch (SQLException | NumberFormatException ex) {
            try {
                throw new InsertErrorException("Unable to Add" + ex.getMessage());
            } catch (InsertErrorException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

}
