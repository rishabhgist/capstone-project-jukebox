package com.niit.jdp.model;

import lombok.Data;

@Data
public class Playlist {
    private String playlistName;
    private String songId;

    public Playlist() {
    }

    public Playlist(String playlistName, String songId) {
        this.playlistName = playlistName;
        this.songId = songId;
    }

}
