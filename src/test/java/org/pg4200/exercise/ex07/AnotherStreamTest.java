package org.pg4200.exercise.ex07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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


}