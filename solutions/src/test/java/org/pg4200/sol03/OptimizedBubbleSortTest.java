package org.pg4200.sol03;


import org.junit.jupiter.api.Test;
import org.pg4200.ex03.GameUser;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class OptimizedBubbleSortTest {

    private OptimizedBubbleSort sorter = new OptimizedBubbleSort();

    private class StringComparator implements Comparator<String>{

        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }


    @Test
    public void testNull(){

        int comparisons = sorter.sort(null, new StringComparator(), false);

        assertEquals(0, comparisons);
    }

    @Test
    public void testAlreadySorted(){

        String[] array = {"a", "b", "c", "d"};

        int comparisons = sorter.sort(array, new StringComparator(), false);

        assertEquals(3, comparisons);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testInverted(){

        String[] array = {"d", "c", "b", "a"};

        int comparisons = sorter.sort(array, new StringComparator(), false);

        assertEquals(12, comparisons);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testBase(){

        String[] array = {"d", "a", "c", "b"};

        sorter.sort(array, new StringComparator(), false);

        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testNearly(){

        String[] array = {"c", "b", "a", "d", "e", "f"};
        int optimized = sorter.sort(array, new StringComparator(), true);

        array = new String[]{"c", "b", "a", "d", "e", "f"};
        int base = sorter.sort(array, new StringComparator(), false);

        assertTrue(optimized < base);
        assertTrue(optimized < base/2);

        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
        assertEquals("e", array[4]);
        assertEquals("f", array[5]);
    }

    @Test
    public void testGameUsers(){

        GameUser a = new GameUser("a", 10);
        GameUser b = new GameUser("b", 5);
        GameUser c = new GameUser("c", 2);
        GameUser d = new GameUser("d", 5);

        GameUser[] array = {a,b,c,d};

        sorter.sort(array, new GameUserComparator(), true);

        assertEquals("c", array[0].getUserId());
        assertEquals("b", array[1].getUserId());
        assertEquals("d", array[2].getUserId());
        assertEquals("a", array[3].getUserId());
    }
}