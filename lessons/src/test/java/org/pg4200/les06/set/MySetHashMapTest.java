package org.pg4200.les06.set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pg4200.les06.set.MySet;
import org.pg4200.les06.set.MySetHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class MySetHashMapTest {


    private MySet<String> set;

    @BeforeEach
    public void initTest() {
        set = new MySetHashMap<>();
    }


    @Test
    public void testEmpty() {

        assertEquals(0, set.size());
        assertTrue(set.isEmpty());
    }

    @Test
    public void testAddOne() {

        int n = set.size();

        set.add("foo");

        assertEquals(n + 1, set.size());
    }


    @Test
    public void testRetrieve() {

        String element = "foo";

        assertFalse(set.isPresent(element));

        set.add(element);

        assertTrue(set.isPresent(element));
    }

    @Test
    public void testAddSameTwice() {

        String element = "foo";

        assertFalse(set.isPresent(element));
        assertEquals(0, set.size());

        set.add(element);
        set.add(element);

        assertTrue(set.isPresent(element));
        assertEquals(1, set.size()); // 1 and not 2
    }

    @Test
    public void testRemove() {

        int n = set.size();

        String element = "foo";

        set.add(element);

        assertTrue(set.isPresent(element));
        assertEquals(n + 1, set.size());

        set.remove(element);

        assertFalse(set.isPresent(element));
        assertEquals(n, set.size());
    }


    @Test
    public void testAddSeveral() {

        set.add("a");
        set.add("a");
        set.add("b");
        set.add("b");
        set.add("c");

        set.add("a");
        set.add("a");
        set.add("b");
        set.add("b");
        set.add("c");

        assertEquals(3, set.size());
        assertTrue(set.isPresent("a"));
        assertTrue(set.isPresent("b"));
        assertTrue(set.isPresent("c"));
    }
}