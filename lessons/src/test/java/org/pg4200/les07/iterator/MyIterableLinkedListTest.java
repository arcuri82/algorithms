package org.pg4200.les07.iterator;


import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 15-Sep-17.
 */
public class MyIterableLinkedListTest {

    @Test
    public void testBaseIterator(){

        MyIterableLinkedList<String> data = new MyIterableLinkedList<>();
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

        MyIterableLinkedList<String> data = new MyIterableLinkedList<>();
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

        MyIterableLinkedList<String> data = new MyIterableLinkedList<>();
        data.add("a");

        Iterator<String> iterator = data.iterator();
        iterator.next();

        data.add("b");

        assertThrows(RuntimeException.class,
                () -> iterator.hasNext());
    }

    // test for base functionality of list

    @Test
    public void testEmpty(){

        MyIterableLinkedList<Integer> data = new MyIterableLinkedList<>();

        //a newly created container should be empty
        assertEquals(0, data.size());
    }


    @Test
    public void testAddOneElement(){

        MyIterableLinkedList<String> data = new MyIterableLinkedList<>();

        int n = data.size();

        data.add("foo");

        assertEquals(n + 1, data.size());
    }

    @Test
    public void testAddAndRetrieveElement() {

        MyIterableLinkedList<String> data = new MyIterableLinkedList<>();

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

        MyIterableLinkedList<String> data = new MyIterableLinkedList<>();

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

        MyIterableLinkedList<String> data = new MyIterableLinkedList<>();

        assertThrows(IndexOutOfBoundsException.class,
                () -> data.get(-5));

        assertThrows(IndexOutOfBoundsException.class,
                () -> data.get(42));
    }

    @Test
    public void testDeleteOne(){

        MyIterableLinkedList<Integer> data = new MyIterableLinkedList<>();

        data.add(1);

        assertEquals(1, data.size());

        data.delete(0);

        assertEquals(0, data.size());
    }

    @Test
    public void testDeleteFirst(){

        MyIterableLinkedList<Integer> data = new MyIterableLinkedList<>();
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

        MyIterableLinkedList<Integer> data = new MyIterableLinkedList<>();
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

        MyIterableLinkedList<Integer> data = new MyIterableLinkedList<>();
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

        MyIterableLinkedList<Integer> data = new MyIterableLinkedList<>();
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