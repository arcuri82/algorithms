package org.pg4200.sol03;

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

        int comparisons = sorter.sort(array, new StringComparator(), false);

        Assert.assertEquals(3, comparisons);
        Assert.assertEquals("a", array[0]);
        Assert.assertEquals("b", array[1]);
        Assert.assertEquals("c", array[2]);
        Assert.assertEquals("d", array[3]);
    }


    @Test
    public void testInverted(){

        String[] array = {"d", "c", "b", "a"};

        int comparisons = sorter.sort(array, new StringComparator(), false);

        Assert.assertEquals(12, comparisons);
        Assert.assertEquals("a", array[0]);
        Assert.assertEquals("b", array[1]);
        Assert.assertEquals("c", array[2]);
        Assert.assertEquals("d", array[3]);
    }


    @Test
    public void testBase(){

        String[] array = {"d", "a", "c", "b"};

        sorter.sort(array, new StringComparator(), false);

        Assert.assertEquals("a", array[0]);
        Assert.assertEquals("b", array[1]);
        Assert.assertEquals("c", array[2]);
        Assert.assertEquals("d", array[3]);
    }


    @Test
    public void testNearly(){

        String[] array = {"c", "b", "a", "d", "e", "f"};
        int optimized = sorter.sort(array, new StringComparator(), true);

        array = new String[]{"c", "b", "a", "d", "e", "f"};
        int base = sorter.sort(array, new StringComparator(), false);

        Assert.assertTrue(optimized < base);
        Assert.assertTrue(optimized < base/2);

        Assert.assertEquals("a", array[0]);
        Assert.assertEquals("b", array[1]);
        Assert.assertEquals("c", array[2]);
        Assert.assertEquals("d", array[3]);
        Assert.assertEquals("e", array[4]);
        Assert.assertEquals("f", array[5]);
    }

}