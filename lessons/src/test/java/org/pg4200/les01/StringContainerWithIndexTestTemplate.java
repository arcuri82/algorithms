package org.pg4200.les01;

import org.junit.Before;
import org.junit.Test;
import org.pg4200.les01.StringContainerWithIndex;

import static org.junit.Assert.*;

/**
 *  Because this class is abstract, we cannot run its tests directly.
 *  We need to extend it first, and implement all of its abstract methods.
 */
public abstract class StringContainerWithIndexTestTemplate {

    /**
     * All tests here are based on an interface.
     * They should work regardless of the chosen implementation.
     * So, here we use an abstract method to specify that the concrete
     * class that will extend this one must provide the chosen implementation
     * here.
     */
    protected abstract StringContainerWithIndex getNewInstance();


    private StringContainerWithIndex data;

    @Before
    public void initTest(){
        //before each test is executed, create a new
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