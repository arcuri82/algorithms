package org.pg4200.ex01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.pg4200.les01.MyListString;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyArrayListIntegerTest {

    /**
     * All tests here are based on an interface.
     * They should work regardless of the chosen implementation.
     * So, here we use an abstract method to specify that the concrete
     * class that will extend this one must provide the chosen implementation
     * here.
     * @return
     */
    protected MyArrayListInteger getNewInstance() {
        return new MyArrayListInteger();
    }


    private MyArrayListInteger list;

    @BeforeEach
    void initTest(){
        //before each test is executed, create a new container
        list = getNewInstance();
    }


    @Test
    void testEmpty(){

        //a newly created container should be empty
        assertEquals(0, list.size());
    }


    @Test
    void testAddOneElement(){

        int n = list.size();

        list.add(15);

        assertEquals(n + 1, list.size());

        /*
            An option here would have to be to test if size is just 1, as
            the container (should) starts empty, instead of n+1.
            However, we would conflate the testing of two different features
            in the same test.
            Here, I am testing that adding an element increases the size by 1,
            regardless of its original size (even though I do actually test it
            with just 0 as starting size, although I could add more tests to handle
            further different cases)
         */
    }

    @Test
    void testAddAndRetrieveElement() {

        int value = 12;

        list.add(value);

        /*
            As the container is empty, I m expecting to have it in position 0
         */
        int res = list.get(0);

        assertEquals(value, res);
    }

    @Test
    void testAdd5Elements(){

        assertEquals(0, list.size());
        int a = 1;
        int b = 2;
        int c=  3;

        list.add(a);
        list.add(b);
        list.add(c);
        list.add(a);
        list.add(b);

        assertEquals(a, list.get(0));
        assertEquals(b, list.get(1));
        assertEquals(c, list.get(2));
        assertEquals(a, list.get(3));
        assertEquals(a, list.get(4));
    }

    @Test
    void testOutOfIndex(){

        assertNull(list.get(-5));
        assertNull(list.get(42));
    }
}
