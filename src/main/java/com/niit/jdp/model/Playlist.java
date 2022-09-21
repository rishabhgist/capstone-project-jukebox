package com.niit.jdp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {
    private int id;
    private String name;
    private List<Song> songList;

    public Playlist() {
        this.songList = new ArrayList<>();
    }

    public Playlist(int id, String name, List<Song> songList) {
        this.id = id;
        this.name = name;
        this.songList = songList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongList() {
        return songList;
    }

    public void setSongList(List<Song> songList) {
        this.songList = songList;
    }

    @Override
    public String toString() {
        return "Playlist{" + "id=" + id + ", name='" + name + '\'' + ", songList=" + songList + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return id == playlist.id && Objects.equals(name, playlist.name) && Objects.equals(songList, playlist.songList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, songList);
    }
}
