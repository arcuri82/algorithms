package org.pg4200.les02.list;

import org.junit.jupiter.api.Test;
import org.pg4200.les02.list.MyList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public abstract class MyListTestTemplate {

    protected abstract <T> MyList<T> getNewInstance(Class<T> klass);



    @Test
    public void testEmpty(){

        MyList<Integer> data = getNewInstance(Integer.class);

        //a newly created list should be empty
        assertEquals(0, data.size());
    }


    @Test
    public void testAddOneElement(){

        MyList<String> data = getNewInstance(String.class);

        int n = data.size();

        data.add("foo");

        assertEquals(n + 1, data.size());
    }

    @Test
    public void testAddAndRetrieveElement() {

        MyList<String> data = getNewInstance(String.class);

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

        MyList<String> data = getNewInstance(String.class);

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

        MyList<String> data = getNewInstance(String.class);

        assertThrows(IndexOutOfBoundsException.class,
                () -> data.get(-5));

        assertThrows(IndexOutOfBoundsException.class,
                () -> data.get(42));
    }

    @Test
    public void testDeleteOne(){

        MyList<Integer> data = getNewInstance(Integer.class);

        data.add(1);

        assertEquals(1, data.size());

        data.delete(0);

        assertEquals(0, data.size());
    }

    @Test
    public void testDeleteFirst(){

        MyList<Integer> data = getNewInstance(Integer.class);
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

        MyList<Integer> data = getNewInstance(Integer.class);
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
    public void testDeleteSecondLast(){

        MyList<Integer> data = getNewInstance(Integer.class);
        data.add(7);
        data.add(31);
        data.add(0);
        data.add(1); //delete this one
        data.add(2);


        assertEquals(5, data.size());

        data.delete(3);

        assertEquals(4, data.size());
        assertEquals(7, data.get(0).intValue());
        assertEquals(31, data.get(1).intValue());
        assertEquals(0, data.get(2).intValue());
        assertEquals(2, data.get(3).intValue());
    }

    @Test
    public void testDeleteMiddle(){

        MyList<Integer> data = getNewInstance(Integer.class);
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

        MyList<Integer> data = getNewInstance(Integer.class);
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

    @Test
    public void testInsertFirst(){

        MyList<String> data = getNewInstance(String.class);
        data.add("a");
        data.add("b");
        data.add("c");

        String value = "foo";
        data.add(0, value);

        assertEquals(4, data.size());
        assertEquals(value, data.get(0));
        assertEquals("a", data.get(1));
        assertEquals("b", data.get(2));
        assertEquals("c", data.get(3));
    }

    @Test
    public void testInsertLast(){

        MyList<String> data = getNewInstance(String.class);
        data.add("a");
        data.add("b");
        data.add("c");

        String value = "foo";
        data.add(3, value);

        assertEquals(4, data.size());
        assertEquals("a", data.get(0));
        assertEquals("b", data.get(1));
        assertEquals("c", data.get(2));
        assertEquals(value, data.get(3));
    }

    @Test
    public void testInsertMiddle(){

        MyList<String> data = getNewInstance(String.class);
        data.add("a");
        data.add("b");
        data.add("c");

        String value = "foo";
        data.add(1, value);

        assertEquals(4, data.size());
        assertEquals("a", data.get(0));
        assertEquals(value, data.get(1));
        assertEquals("b", data.get(2));
        assertEquals("c", data.get(3));
    }
}
