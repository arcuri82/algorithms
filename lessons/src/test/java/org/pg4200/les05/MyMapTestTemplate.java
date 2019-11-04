package org.pg4200.les05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created by arcuri82 on 22-Aug-17.
 */
public abstract class MyMapTestTemplate {

    protected abstract <K extends Comparable<K>, V> MyMap<K, V> getInstance();

    private MyMap<String, Integer> map;

    @BeforeEach
    public void initTest() {
        map = getInstance();
    }

    @Test
    public void testEmpty() {

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    public void testAddOne() {

        int n = map.size();

        map.put("foo", 1);

        assertEquals(n + 1, map.size());
    }


    @Test
    public void testRetrieve() {

        String key = "foo";
        int value = 42;

        assertNull(map.get(key));

        map.put(key, value);

        int res = map.get(key);

        assertEquals(value, res);
    }

    @Test
    public void testUpdateOld() {

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
            map = getInstance();

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
                assertEquals(size, map.size(), "" + keys);
            }
        }
    }

    /*
        Once you get some random key sequences failing in previous test,
        then you can create specific tests for them to help debugging
     */
    @Test
    public void test_2_1_3_0_4() {

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


    @Test
    public void testSearchNullOnNonEmpty(){

        map.put("foo", 1);

        assertThrows(NullPointerException.class, () -> map.get(null));
    }

    @Test
    public void testSearchNull(){
        assertThrows(NullPointerException.class, () -> map.get(null));
    }

    @Test
    public void testDeleteNull(){
        assertThrows(NullPointerException.class, () -> map.delete(null));
    }

    @Test
    public void testPutNull(){
        assertThrows(NullPointerException.class, () -> map.put(null,42));
    }
}