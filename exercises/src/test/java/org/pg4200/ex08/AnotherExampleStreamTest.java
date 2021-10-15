package org.pg4200.ex08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnotherExampleStreamTest {

    private AnotherExampleStreamList<String> initTestData(){
        AnotherExampleStreamList<String> list = new AnotherExampleStreamList<>();

        list.add("a");
        list.add("a");
        list.add("strange");
        list.add("things");
        list.add("with");
        list.add("streams");
        list.add("streams");
        list.add("streams");

        return list;
    }

    @Test
    public void testCount(){

        AnotherExampleStreamList<String> list = initTestData();

        int result = list.stream().count();

        assertEquals(list.size(), result);
    }

    @Test
    public void testDistinct(){
        AnotherExampleStreamList<String> list = initTestData();

        int nondistinct = list.stream().count();
        int result = list.stream().distinct().count();

        assertTrue(result <= nondistinct);
        assertEquals(5, result);

    }

    @Test
    public void foundAnyTest(){
        AnotherExampleStreamList<String> list = initTestData();

        boolean anyEmpty = list.stream().any(s -> s.isEmpty());
        boolean anyStream = list.stream().any(s -> s.equalsIgnoreCase("stream"));
        boolean anyContainsStream = list.stream().any(s -> s.contains("stream"));

        assertFalse(anyEmpty);
        assertFalse(anyStream);
        assertTrue(anyContainsStream);

        assertTrue(list.stream().any(s -> s.contains("strange")));

    }

    @Test
    public void testJoinToString(){
        AnotherExampleStreamList<String> list = initTestData();

        String result = list.stream().joinToString(" -> ");

        String hihi = list.stream().joinToString(" . ");

        assertTrue(result.contains(" -> "));
        assertTrue(hihi.contains(" . "));
        assertFalse(hihi.contains("->"));
        assertFalse(result.contains("."));
        assertEquals("a -> a -> strange -> things -> with -> streams -> streams -> streams",
                result);
        assertEquals("a . a . strange . things . with . streams . streams . streams",
                hihi);
    }
}
