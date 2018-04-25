package org.pg4200.datastructure.iterator;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 15-Sep-17.
 */
public class IterableLinkedListTest {

    @Test
    public void testBaseIterator(){

        IterableLinkedList<String> data = new IterableLinkedList<>();
        data.add("a");
        data.add("b");
        data.add("c");

        String buffer = "";

        Iterator<String> iterator = data.iterator();
        while(iterator.hasNext()){
            String value = iterator.next();
            buffer += value;
        }

        assertEquals("abc", buffer);
    }


    @Test
    public void testIterable(){

        IterableLinkedList<String> data = new IterableLinkedList<>();
        data.add("a");
        data.add("b");
        data.add("c");

        String buffer = "";

        for(String value : data){
            buffer += value;
        }

        assertEquals("abc", buffer);
    }

    @Test
    public void testModifications(){

        IterableLinkedList<String> data = new IterableLinkedList<>();
        data.add("a");

        Iterator<String> iterator = data.iterator();
        iterator.next();

        data.add("b");

        try{
            iterator.hasNext();
            fail();
        }catch (RuntimeException e){
            //expected
        }
    }

    // test for base functionality of list

    @Test
    public void testEmpty(){

        IterableLinkedList<Integer> data = new IterableLinkedList<>();

        //a newly created container should be empty
        assertEquals(0, data.size());
    }


    @Test
    public void testAddOneElement(){

        IterableLinkedList<String> data = new IterableLinkedList<>();

        int n = data.size();

        data.add("foo");

        assertEquals(n + 1, data.size());
    }

    @Test
    public void testAddAndRetrieveElement() {

        IterableLinkedList<String> data = new IterableLinkedList<>();

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

        IterableLinkedList<String> data = new IterableLinkedList<>();

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

        IterableLinkedList<String> data = new IterableLinkedList<>();

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

        IterableLinkedList<Integer> data = new IterableLinkedList<>();

        data.add(1);

        assertEquals(1, data.size());

        data.delete(0);

        assertEquals(0, data.size());
    }

    @Test
    public void testDeleteFirst(){

        IterableLinkedList<Integer> data = new IterableLinkedList<>();
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

        IterableLinkedList<Integer> data = new IterableLinkedList<>();
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

        IterableLinkedList<Integer> data = new IterableLinkedList<>();
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

        IterableLinkedList<Integer> data = new IterableLinkedList<>();
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