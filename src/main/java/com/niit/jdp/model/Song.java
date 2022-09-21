package com.niit.jdp.model;

import java.util.Objects;

public class Song {
    private int id;
    private String name;
    private String genre;
    private double length;
    private String artist;

    private String album;

    private String path;

    public Song() {
    }

    public Song(int id, String name, String genre, double length, String artist, String album, String path) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.length = length;
        this.artist = artist;
        this.album = album;
        this.path = path;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return id + name + artist + genre + length + album;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return Double.compare(song.length, length) == 0 && Objects.equals(name, song.name) && Objects.equals(genre, song.genre) && Objects.equals(artist, song.artist) && Objects.equals(album, song.album) && Objects.equals(path, song.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, length, artist, album, path);
    }
}
