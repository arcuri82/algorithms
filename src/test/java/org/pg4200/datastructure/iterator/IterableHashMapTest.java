package org.pg4200.datastructure.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class IterableHashMapTest {

    @Test
    public void testBaseIterator(){

        IterableHashMap<Integer, String>  map = new IterableHashMap<>();
        map.put(0, "a");
        map.put(1, "b");
        map.put(2, "c");

        String buffer = "";

        Iterator<String> iterator = map.iterator();
        while(iterator.hasNext()){
            String value = iterator.next();
            buffer += value;
        }

        /*
            Note: here I can't really make assumptions on which order
            the 3 values will be retrieved with the iterator.
         */

        assertEquals(3, buffer.length());
        assertTrue(buffer.contains("a"));
        assertTrue(buffer.contains("b"));
        assertTrue(buffer.contains("c"));
    }


    @Test
    public void testIterable(){

        IterableHashMap<Integer, String>  map = new IterableHashMap<>();
        map.put(0, "a");
        map.put(1, "b");
        map.put(2, "c");

        String buffer = "";

        for(String value : map){
            buffer += value;
        }

        assertEquals(3, buffer.length());
        assertTrue(buffer.contains("a"));
        assertTrue(buffer.contains("b"));
        assertTrue(buffer.contains("c"));
    }

    @Test
    public void testModifications(){

        IterableHashMap<Integer, String>  map = new IterableHashMap<>();
        map.put(0, "a");

        Iterator<String> iterator = map.iterator();
        iterator.next();

        map.put(1, "b");

        try{
            iterator.hasNext();
            fail();
        }catch (RuntimeException e){
            //expected
        }
    }



    // test for base functionality of map
    @Test
    public void testEmpty() {

        IterableHashMap<String, Integer>  map = new IterableHashMap<>();

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    public void testAddOne() {

        IterableHashMap<String, Integer>  map = new IterableHashMap<>();

        int n = map.size();

        map.put("foo", 1);

        assertEquals(n + 1, map.size());
    }


    @Test
    public void testRetrieve() {

        IterableHashMap<String, Integer>  map = new IterableHashMap<>();

        String key = "foo";
        int value = 42;

        assertNull(map.get(key));

        map.put(key, value);

        int res = map.get(key);

        assertEquals(value, res);
    }

    @Test
    public void testUpdateOld() {

        IterableHashMap<String, Integer>  map = new IterableHashMap<>();

        String key = "foo";
        int x = 42;

        map.put(key, x);

        int y = 66;

        //old value x should be replaced now by y
        map.put(key, y);

        int res = map.get(key);

        assertEquals(y, res);
    }

    @Test
    public void testDelete() {

        IterableHashMap<String, Integer>  map = new IterableHashMap<>();

        int n = map.size();

        String key = "foo";
        int x = 42;

        map.put(key, x);

        assertEquals(x, map.get(key).intValue());
        assertEquals(n + 1, map.size());

        map.delete(key);

        assertNull(map.get(key));
        assertEquals(n, map.size());
    }


    @Test
    public void testAddSeveral() {

        IterableHashMap<String, Integer>  map = new IterableHashMap<>();

        map.put("a", 0);
        map.put("a", 0);
        map.put("b", 1);
        map.put("b", 1);
        map.put("c", 2);

        map.put("a", 0);
        map.put("a", 0);
        map.put("b", 1);
        map.put("b", 1);
        map.put("c", 2);

        assertEquals(3, map.size());
        assertEquals(0, map.get("a").intValue());
        assertEquals(1, map.get("b").intValue());
        assertEquals(2, map.get("c").intValue());
    }

    @Test
    public void testRandom() {

        int n = 100; //if you get failures, you can use lower number to help debugging, eg 5
        List<String> keys = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            keys.add("" + i);
        }

        for (int i = 0; i < 10_000; i++) {
            Collections.shuffle(keys);
            IterableHashMap<String, Integer>  map = new IterableHashMap<>();

            for (String key : keys) {
                map.put(key, 0);
            }

            assertEquals(keys.size(), map.size());
            for (String key : keys) {
                assertNotNull(map.get(key));
            }

            int size = keys.size();
            Collections.shuffle(keys);

            for (String key : keys) {
                map.delete(key);
                assertNull(map.get(key));
                size--;
                assertEquals("" + keys, size, map.size());
            }
        }
    }

    @Test
    public void test_2_1_3_0_4() {

        IterableHashMap<String, Integer>  map = new IterableHashMap<>();

        String[] keys = {"2", "1", "3", "0", "4"};
        for (String key : keys) {
            map.put(key, 0);
        }

        int size = keys.length;

        for (String key : keys) {
            map.delete(key);
            assertNull(map.get(key));
            size--;
            assertEquals(size, map.size());
        }
    }
}