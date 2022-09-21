package com.niit.jdp.service;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CatalogueService {
    Song song;
    SongRepository songRepository;
    DatabaseService databaseService;

    public CatalogueService() throws SQLException {
        databaseService = new DatabaseService();
        songRepository = new SongRepository();
    }

    public void displayMusicMenu() {
        List<Song> songList = songRepository.displayAllSong(databaseService.getConnection());
        int serial = 1;
        displayHeader();
        System.out.println("Sr No. Song Title          Artist          Genre       Duration    Album       ");
        for (Song fetchSong : songList) {
            System.out.println(serial + fetchSong.toString());
            serial++;
        }
    }

    public void printDefault() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
            displayHeader();
            System.out.println("1.View Songs List\n2.View Playlists\n3.Sort song by alphabet\n4.Find a song");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    displayMusicMenu();
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
