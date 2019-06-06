package org.pg4200.les03.sort;

import org.junit.jupiter.api.Test;
import org.pg4200.les03.sort.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class SongTest {

    @Test
    public void testComparator(){

        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Foo","a",0));
        songs.add(new Song("Foo","b",3));
        songs.add(new Song("Bar","c",0));

        Collections.sort(songs);

        //highest popularity
        assertEquals("Foo", songs.get(0).getArtist());
        assertEquals("b", songs.get(0).getTitle());
        assertEquals(3, songs.get(0).getPopularity());

        //same popularity as "a", but artist name "Bar" comes before "Foo"
        assertEquals("c", songs.get(1).getTitle());

        assertEquals("a", songs.get(2).getTitle());
    }

    @Test
    public void testCustomComparator(){

        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Foo","a",0));
        songs.add(new Song("Foo","b",3));
        songs.add(new Song("Bar","c",0));

        /*
            The class Song has a specified way in which
            its instances are sorted.
            However, when sorting, we can use a custom
            comparator created on the fly.
         */
        Collections.sort(songs, (s1, s2) ->
                - s1.getTitle().compareTo(s2.getTitle()));


        assertEquals("c", songs.get(0).getTitle());
        assertEquals("b", songs.get(1).getTitle());
        assertEquals("a", songs.get(2).getTitle());
    }


    @Test
    public void testStable(){

        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Foo","a",0));
        songs.add(new Song("Foo","b",3));
        songs.add(new Song("Bar","c",0));

        Collections.sort(songs, Comparator.comparing(Song::getArtist));


        assertEquals("Bar", songs.get(0).getArtist());
        assertEquals("Foo", songs.get(1).getArtist());
        assertEquals("Foo", songs.get(2).getArtist());


        assertEquals("c", songs.get(0).getTitle());

        /*
            The comparator did not distinguish between "a"
            and "b" because they both have the same author
            "Foo". "Stable" here means that the order of the
            original list is maintained when no ordering decision
            is made. So, if "a" ended up as last element, the list
            would still be sorted, but not in a "stable" way.
         */
        assertEquals("a", songs.get(1).getTitle());
        assertEquals("b", songs.get(2).getTitle());
    }
}