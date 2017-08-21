package org.pg4200.sorting;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public abstract class SortTestTemplate {

    protected abstract MySort getInstance();

    protected MySort sorter;

    @Before
    public void init(){
        sorter = getInstance();
    }


    @Test
    public void testAlreadySorted(){

        String[] array = new String[]{"a", "b", "c", "d"};

        sorter.sort(array);

        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testInverted(){

        String[] array = new String[]{"d", "c", "b", "a"};

        sorter.sort(array);

        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testBase(){

        String[] array = new String[]{"d", "a", "c", "b"};

        sorter.sort(array);

        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }

}
