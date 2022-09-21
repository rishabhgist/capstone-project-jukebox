package com.niit.jdp;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.CatalogueRepository;
import com.niit.jdp.service.DatabaseService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseService databaseService = new DatabaseService();
            CatalogueRepository catalogue = new CatalogueRepository();
            List<Song> songs = catalogue.displayCatalogue(databaseService.getConnection());
            for (Song song : songs) {
                System.out.println(song.toString());
            }

        } catch (SQLException e) {
            System.err.println("Unable to connect to Database, " + e.getMessage());
        }

    }
}