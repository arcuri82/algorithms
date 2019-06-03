package org.pg4200.sol01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListIntegerTest {

    private MyArrayListInteger data;

    @BeforeEach
    public void initTest(){
        //before each test is executed, create a new
        data = new MyArrayListInteger(10);
    }


    @Test
    public void testEmpty(){

        //a newly created container should be empty
        assertEquals(0, data.size());
    }


    @Test
    public void testAddOneElement(){

        int n = data.size();

        data.add(2);

        assertEquals(n + 1, data.size());
    }

    @Test
    public void testAddAndRetrieveElement() {

        int value = 3;

        data.add(value);

        /*
            As the container is empty, I m expecting to have it in position 0
         */
        int res = data.get(0);

        assertEquals(value, res);
    }

    @Test
    public void testAdd5Elements(){

        assertEquals(0, data.size());
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;

        data.add(a);
        data.add(b);
        data.add(c);
        data.add(a);
        data.add(a);

        assertEquals(a, data.get(0));
        assertEquals(b, data.get(1));
        assertEquals(c, data.get(2));
        assertEquals(a, data.get(3));
        assertEquals(a, data.get(4));
    }

    @Test
    public void testOutOfIndex(){

        assertNull(data.get(-5));
        assertNull(data.get(42));
    }


}