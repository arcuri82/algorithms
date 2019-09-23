package org.pg4200.sol07;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class AnotherStreamTest {


    private AnotherStreamList<String> getData() {
        AnotherStreamList<String> list = new AnotherStreamList<>();
        list.add("a");
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");
        return list;
    }

    @Test
    public void testCount() {

        AnotherStreamList<String> list = getData();

        int res = list.stream().count();

        assertEquals(list.size(), res);
    }

    @Test
    public void testSkip() {

        AnotherStreamList<String> list = getData();

        assertEquals(5, list.stream().skip(0).count());
        assertEquals(4, list.stream().skip(1).count());
        assertEquals(3, list.stream().skip(2).count());
        assertEquals(2, list.stream().skip(3).count());
        assertEquals(1, list.stream().skip(4).count());
        assertEquals(0, list.stream().skip(5).count());
    }

    @Test
    public void testDistinct() {

        AnotherStreamList<String> list = getData();

        int res = list.stream().distinct().count();

        assertEquals(3, res);
    }

    @Test
    public void testCombination() {

        AnotherStreamList<String> list = getData();

        int res = list.stream()
                .skip(1)
                .distinct()
                .count();

        assertEquals(3, res);

        res = list.stream()
                .distinct()
                .skip(1)
                .count();

        assertEquals(2, res);
    }

    @Test
    public void testJoinToString(){

        AnotherStreamList<String> list = getData();
        list.add(null);
        list.add("z");

        String res = list.stream().joinToString(",");

        assertEquals("a,a,b,c,c,,z", res);
    }

    @Test
    public void testAny(){

        AnotherStreamList<String> list = getData();

        assertTrue(list.stream().any(it -> it!=null));
        assertTrue(list.stream().any(it -> it.equals("a")));
        assertFalse(list.stream().any(it -> it.equals("k")));
    }


    @Test
    public void testSorting(){

        AnotherStreamList<String> list = new AnotherStreamList<>();
        list.add("e");
        list.add("a");
        list.add("b");
        list.add("d");
        list.add("c");

        String res = list.stream().sorted().joinToString("");

        assertEquals("abcde", res);
    }


    @Test
    public void testSortedCombination(){

        AnotherStreamList<String> list = new AnotherStreamList<>();
        list.add("e");
        list.add("a");
        list.add("b");
        list.add("d");
        list.add("c");

        String res = list.stream()
                .skip(2)
                .sorted()
                .skip(1)
                .joinToString("");

        assertEquals("cd", res);
    }


    private class Foo{}

    @Test
    public void testSortedFailed(){

        AnotherStreamList<Foo> list = new AnotherStreamList<>();
        list.add(new Foo());
        list.add(new Foo());
        list.add(new Foo());

        assertThrows(RuntimeException.class, () -> list.stream().sorted().count());
    }
}