package org.pg4200.les01;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 *  Because this class is abstract, we cannot run its tests directly.
 *  We need to extend it first, and implement all of its abstract methods.
 */
public abstract class MyStringContainerWithIndexTestTemplate {

    /**
     * All tests here are based on an interface.
     * They should work regardless of the chosen implementation.
     * So, here we use an abstract method to specify that the concrete
     * class that will extend this one must provide the chosen implementation
     * here.
     */
    protected abstract MyStringContainerWithIndex getNewInstance();


    private MyStringContainerWithIndex data;

    @BeforeEach
    public void initTest(){
        //before each test is executed, create a new container
        data = getNewInstance();
    }


    @Test
    public void testEmpty(){

        //a newly created container should be empty
        assertEquals(0, data.size());
    }


    @Test
    public void testAddOneElement(){

        int n = data.size();

        data.add("foo");

        assertEquals(n + 1, data.size());

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
    public void testAddAndRetrieveElement() {

        String value = "foo";

        data.add(value);

        /*
            As the container is empty, I m expecting to have it in position 0
         */
        String res = data.get(0);

        assertEquals(value, res);
    }

    @Test
    public void testAdd5Elements(){

        assertEquals(0, data.size());
        String a = "a";
        String b = "b";
        String c = "c";

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