package com.niit.jdp.model;

import lombok.Data;

@Data
public class Song {
    private String songName;
    private String songGenre;
    private double songLength;
    private String songArtist;
}
