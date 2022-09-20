package com.niit.jdp.model;

import lombok.Data;

@Data
public class Song {
    private String songName;
    private String songGenre;
    private double songLength;
    private String songArtist;

    public Song() {
    }

    public Song(String songName, String songGenre, double songLength, String songArtist) {
        this.songName = songName;
        this.songGenre = songGenre;
        this.songLength = songLength;
        this.songArtist = songArtist;
    }


}
