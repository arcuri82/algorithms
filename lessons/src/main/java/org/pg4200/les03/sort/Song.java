package org.pg4200.les03.sort;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class Song implements Comparable<Song> {

    private final String artist;
    private final String title;
    private final int popularity;

    public Song(String artist, String title, int popularity) {
        this.artist = artist;
        this.title = title;
        this.popularity = popularity;
    }

    /*
        You might need to sort complex objects.
        But you need to define how an element X
        is compared to another Y.
        If same, the comparison should return 0.
        Otherwise a negative number (eg -1) if lower,
        or positive if higher.
     */
    @Override
    public int compareTo(Song other) {

        int comp = other.popularity - popularity;
        if (comp != 0) {
            return comp;
        }

        comp = artist.compareTo(other.artist);
        if (comp != 0) {
            return comp;
        }

        return title.compareTo(other.title);
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public int getPopularity() {
        return popularity;
    }
}



