package org.pg4200.exercise.ex03;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class OptimizedBubbleSortTest {

    private OptimizedBubbleSort sorter = new OptimizedBubbleSort();

    class StringComparator implements Comparator<String>{

        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }


    @Test
    public void testAlreadySorted(){

        String[] array = {"a", "b", "c", "d"};

        int comparsions = sorter.sort(array, new StringComparator(), false);

        assertEquals(3, comparsions);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testInverted(){

        String[] array = {"d", "c", "b", "a"};

        int comparsions = sorter.sort(array, new StringComparator(), false);

        assertEquals(12, comparsions);
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

}