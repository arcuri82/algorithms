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

        assertEquals(8, result);
    }

    @Test
    public void testDistinct(){
        AnotherExampleStreamList<String> list = initTestData();

        int nondistinct = list.stream().count();
        int result = list.stream().distinct().count();

        assertTrue(result <= nondistinct);
        assertEquals(5, result);

    }
}
