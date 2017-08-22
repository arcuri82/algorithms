package org.pg4200.datastructure.map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 22-Aug-17.
 */
public abstract class MyMapTestTemplate {

    protected abstract <K extends Comparable<K>,V> MyMap<K,V>  getInstance();

    private MyMap<String, Integer> map;

    @Before
    public void initTest(){
        map = getInstance();
    }

    @Test
    public void testEmpty(){

        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    public void testAddOne(){

        int n = map.size();

        map.put("foo", 1);

        assertEquals(n +1, map.size());
    }


    @Test
    public void testRetrieve(){

        String key = "foo";
        int value = 42;

        assertNull(map.get(key));

        map.put(key, value);

        int res = map.get(key);

        assertEquals(value, res);
    }

    @Test
    public void testUpdateOld(){

        String key = "foo";
        int x = 42;

        map.put(key, x);

        int y = 66;

        //old value x should be replaced now by y
        map.put(key, y);

        int res = map.get(key);

        assertEquals(y, res);
    }

}