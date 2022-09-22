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

    SongRepository songRepository;
    DatabaseService databaseService;
    PlaylistRepository playlistRepository;

    Playlist playlist;
    List<Song> songList;
    SongPlayer songPlayer;
    String title;
    Scanner input;

    public CatalogueService() throws SQLException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
        playlistRepository = new PlaylistRepository();
        songPlayer = new SongPlayer();
        songList = new ArrayList<>();
        input = new Scanner(System.in);
        playlist = new Playlist();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void printDefault() throws SQLException, InsertErrorException {
        int choice;
        setTitle("The Jukebox");
        displayHeader();
        do {
            songList = songRepository.displayAll(databaseService.getConnection());
            System.out.println("\nPlease select from below options or enter 0 to exit");
            System.out.println("1.Song\n2.Playlists\n0.Exit");
            choice = input.nextInt();

            switch (choice) {
                case 1 -> songCatalogue();
                case 2 -> playlistCatalogue();
                case 0 -> System.out.println("Thanks for using");
                default -> System.err.println("Invalid choice");
            }
        } while (choice != 0);
    }

    public void songCatalogue() throws SQLException, InsertErrorException {
        int choice;
        do {
            System.out.println("\nPlease select from below options or enter 0 to go back");
            System.out.println("1.View All Songs\n2.Add Song\n3.Delete Song\n4.Find song by name\n0.Exit");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> songDisplayFormat(songList);
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
                    String findName = input.next();
                    List<Song> songList1 = new ArrayList<>();
                    songList1.add(songRepository.displayByName(databaseService.getConnection(), findName));
                    songDisplayFormat(songList1);
                }
                case 5 -> {
                    List<Song> sortedSong = songRepository.sortByAlphabet(songList);
                    songDisplayFormat(sortedSong);
                }
                case 0 -> System.out.println();
                default -> System.err.println("Invalid Choice");
            }

        } while (choice != 0);
    }

    public void playlistCatalogue() throws SQLException, InsertErrorException {
        List<Playlist> playlistLists = playlistRepository.displayAll(databaseService.getConnection());
        int choice;
        do {
            System.out.println("\nPlease select below options or 0 to go back");
            System.out.println("1.View All Playlist\n2.Add Playlist\n3.Delete Playlist\n0.Go Back");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> playlistDisplayFormat(playlistLists);
                case 2 -> {
                    if (createPlaylist()) {
                        System.out.println("Playlist added Successfully");
                    } else System.err.println("Unable to add Playlist");
                }
                case 3 -> {
                    System.out.println("Enter Playlist Id to Delete");
                    int id = input.nextInt();
                    if (playlistRepository.delete(databaseService.getConnection(), id)) {
                        System.out.println("Deleted Successfully");
                    } else System.err.println("Unable to delete, please check playlist id");
                }
                case 0 -> System.out.println();
                default -> System.err.println("Invalid Option");
            }
        } while (choice != 0);
    }

    public void playSong(int songId) {
        for (Song songObj : songList) {
            if (songObj.getId() == songId) {
                songPlayer.setFilePath(songObj.getPath());
                if (!songPlayer.isClipStatus()) {
                    songPlayer.stop();
                }
                playerControl();
            }
        }

    }

    public void displayHeader() {
        System.out.println("=============================================================================");
        System.out.println("||                               " + getTitle() + "\t                         ||");
        System.out.println("=============================================================================");
    }

    public boolean getInputFromUserAndAdd() throws InsertErrorException {
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
            throw new InsertErrorException("Unable to add" + ex.getMessage());
        }
    }

    public boolean createPlaylist() throws InsertErrorException {
        List<Song> songs = new ArrayList<>();
        String songFormat = "%-7s %-20s %-16s %-10s %-10s %-1s";
        System.out.println("\nSongs List");
        System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album\n");
        songList.forEach(val -> System.out.format(songFormat, val.getId(), val.getName(), val.getArtist(), val.getGenre(), val.getLength(), val.getAlbum() + "\n"));
        System.out.println("Enter the name of the Playlist");
        playlist.setName(input.next());
        int choice;
        do {
            System.out.println("Enter the id of song you want to add or press 0 to go back");
            choice = input.nextInt();
            for (Song val : songList) {
                if (val.getId() == choice) {
                    songs.add(val);
                }
            }
        } while (choice != 0);
        playlist.setSongList(songs);
        playlistDisplayFormat(playlistRepository.displayAll(databaseService.getConnection()));
        return playlistRepository.add(databaseService.getConnection(), playlist);
    }

    public void playerControl() {
        int choice;
        do {
            String status = "Pause";
            if (songPlayer.isPauseStatus()) {
                status = "Play";
            }
            System.out.println("1." + status + "\n2.Stop");
            choice = input.nextInt();
            if (choice == 1) {
                songPlayer.pause();
            } else if (choice == 2) {
                songPlayer.stop();
            } else {
                System.err.println("Invalid choice");
            }
        } while (choice != 2);
    }

    public void songDisplayFormat(List<Song> songList) {
        int choice;
        System.out.println("\n\nPlease select from below options or enter 0 to go back");
        String songFormat = "%-7s %-20s %-16s %-10s %-10s %-1s";
        System.out.format(songFormat, "Sr No.", "Song Title", "Artist", "Genre", "Duration", "Album\n");
        songList.forEach(val -> System.out.format(songFormat, val.getId(), val.getName(), val.getArtist(), val.getGenre(), val.getLength(), val.getAlbum() + "\n"));
        System.out.println("Enter the song id you want to play");
        choice = input.nextInt();
        playSong(choice);
    }

    public void playlistDisplayFormat(List<Playlist> playlistLists) {
        String playlistFormat = "%-10s %-15s %-1s";
        setTitle("Jukebox Playlist");
        displayHeader();
        System.out.format(playlistFormat, "Sr. No", "Playlist Name", "Total Songs\n");
        for (Playlist playlistList : playlistLists) {
            System.out.format(playlistFormat, playlistList.getId(), playlistList.getName(), playlistList.getSongList().size() + "\n");
        }
        System.out.println("Enter the playlist you want to play");
        int playlistSelect = input.nextInt();
        List<Song> songList1 = new ArrayList<>();
        for (Playlist values : playlistLists) {
            if (values.getId() == playlistSelect) {
                setTitle(values.getName());
                songList1 = values.getSongList();
            }
        }
        displayHeader();
        songDisplayFormat(songList1);
    }
}
