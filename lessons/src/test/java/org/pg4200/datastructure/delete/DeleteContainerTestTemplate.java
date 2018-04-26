package org.pg4200.datastructure.delete;

import org.junit.Test;
import org.pg4200.les02.delete.DeleteContainer;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public abstract class DeleteContainerTestTemplate {

    protected abstract <T> DeleteContainer<T> getNewInstance(Class<T> klass);



    @Test
    public void testEmpty(){

        DeleteContainer<Integer> data = getNewInstance(Integer.class);

        //a newly created container should be empty
        assertEquals(0, data.size());
    }


    @Test
    public void testAddOneElement(){

        DeleteContainer<String> data = getNewInstance(String.class);

        int n = data.size();

        data.add("foo");

        assertEquals(n + 1, data.size());
    }

    @Test
    public void testAddAndRetrieveElement() {

        DeleteContainer<String> data = getNewInstance(String.class);

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

        DeleteContainer<String> data = getNewInstance(String.class);

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

        DeleteContainer<String> data = getNewInstance(String.class);

        try {
            assertNull(data.get(-5));
            fail();
        } catch (IndexOutOfBoundsException e){
            //expected
        }

        try {
            assertNull(data.get(42));
            fail();
        } catch (IndexOutOfBoundsException e){
            //expected
        }
    }

    @Test
    public void testDeleteOne(){

        DeleteContainer<Integer> data = getNewInstance(Integer.class);

        data.add(1);

        assertEquals(1, data.size());

        data.delete(0);

        assertEquals(0, data.size());
    }

    @Test
    public void testDeleteFirst(){

        DeleteContainer<Integer> data = getNewInstance(Integer.class);
        data.add(7);
        data.add(31);
        data.add(0);

        assertEquals(3, data.size());
        assertEquals(7, data.get(0).intValue());

        //recall, "0" here is the index, NOT the value stored
        data.delete(0);

        assertEquals(2, data.size());
        assertEquals(31, data.get(0).intValue());
        assertEquals(0, data.get(1).intValue());
    }

    @Test
    public void testDeleteLast(){

        DeleteContainer<Integer> data = getNewInstance(Integer.class);
        data.add(7);
        data.add(31);
        data.add(0);

        assertEquals(3, data.size());

        data.delete(2);

        assertEquals(2, data.size());
        assertEquals(7, data.get(0).intValue());
        assertEquals(31, data.get(1).intValue());
    }

    @Test
    public void testDeleteMiddle(){

        DeleteContainer<Integer> data = getNewInstance(Integer.class);
        data.add(7);
        data.add(31);
        data.add(0);

        assertEquals(3, data.size());

        data.delete(1);

        assertEquals(2, data.size());
        assertEquals(7, data.get(0).intValue());
        assertEquals(0, data.get(1).intValue());
    }


    @Test
    public void testDeleteAll(){

        DeleteContainer<Integer> data = getNewInstance(Integer.class);
        data.add(7);
        data.add(31);
        data.add(0);
        data.add(2);
        data.add(1);

        assertEquals(5, data.size());

        /*
            Why do I need to store the size of the container here
            in a variable instead of using "data.size()" directly
            inside the for loop?
            Reason: at each iteration of the loop, the size decreases...
            Another way to write it could had been a while loop with
            condition "data.size()>0"
         */
        int n = data.size();

        for(int i=0; i<n; i++) {
            data.delete(0);
        }

        assertTrue(data.isEmpty());
    }
}
