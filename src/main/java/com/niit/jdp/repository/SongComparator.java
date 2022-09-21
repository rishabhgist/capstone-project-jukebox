package com.niit.jdp.repository;

import com.niit.jdp.model.Song;

import java.util.Comparator;

public class SongComparator implements Comparator<Song> {


    /**
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return integer value 0, 1, -1
     */
    @Override
    public int compare(Song o1, Song o2) {
        return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
    }
}
