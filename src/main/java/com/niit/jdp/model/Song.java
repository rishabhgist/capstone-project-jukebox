package com.niit.jdp.model;

import lombok.Data;

@Data
public class Song {
    private String songName;
    private String songGenre;
    private double songLength;
    private String songArtist;

    private String songAlbum;

    public Song() {
    }

    public Song(String songName, String songGenre, double songLength, String songArtist, String songAlbum) {
        this.songName = songName;
        this.songGenre = songGenre;
        this.songLength = songLength;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
    }
}
