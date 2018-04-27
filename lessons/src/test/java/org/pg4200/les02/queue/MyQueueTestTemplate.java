package org.pg4200.les02.queue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public abstract class MyQueueTestTemplate {

    protected abstract <T> MyQueue<T> getNewInstance(Class<T> klass);

    protected MyQueue<Integer> queue;

    @BeforeEach
    public void init(){
        queue = getNewInstance(Integer.class);
    }

    @Test
    public void testEmpty(){
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testEnqueue(){

        assertEquals(0, queue.size());

        queue.enqueue(1);

        assertEquals(1, queue.size());
    }


    @Test
    public void testFailToDequeueOnEmpty(){

        assertThrows(RuntimeException.class,
                () -> queue.dequeue());
    }


    @Test
    public void testInsertAndPeek(){

        int n = 42;

        queue.enqueue(n);

        int res = queue.peek();

        assertEquals(n, res);
    }


    @Test
    public void testInsertAndRemove(){

        int n = 42;

        assertEquals(0, queue.size());

        queue.enqueue(n);

        assertEquals(1, queue.size());

        int res = queue.dequeue();

        assertEquals(n, res);
        assertEquals(0, queue.size());
    }


    @Test
    public void testInsertAndRemoveThreeTimes(){

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(0, queue.dequeue().intValue());
        assertEquals(1, queue.dequeue().intValue());
        assertEquals(2, queue.dequeue().intValue());
    }

    /*
        Note: the following tests are meant to test the internal
        details of "ArrayQueue#enqueue".
        However, as we work on MyQueue reference, the tests should
        still work on the other implementations as well.

        However, we have no way from MyQueue to check if the different
        "if" statements inside "ArrayQueue#enqueue" are actually
        executed.
        Exposing the internal details of ArrayQueue would be wrong from
        an OO encapsulation point of view.
        This is issue is resolved by calculating what is called "code coverage".
        Eg, if you are using IntelliJ, you can run the tests "with Coverage".
        In software development, you would use something called Continuous Integration (CI),
        where tests are run automatically, and the build/compilation of the project
        can be configured to fail if not enough coverage is achieved.
        However, will not see CI in this course.
     */

    @Test
    public void testManyInsertionsWithSmallSize(){

        queue.enqueue(0);

        for(int i=0; i< 1_000; i++){
            queue.enqueue(42);
            queue.dequeue();
        }

        assertEquals(1, queue.size());
        assertEquals(42, queue.peek().intValue());
    }

    @Test
    public void testManyInsertionWithLargeSize(){

        int n = 1_000;

        for(int i=0; i< n; i++){
            queue.enqueue(i);
            queue.enqueue(i);
            queue.dequeue();
            //ie, insert 2 elements, and then remove 1
        }

        assertEquals(n, queue.size());
        assertEquals(n/2, queue.peek().intValue());
    }

    @Test
    public void testLargeQueue(){

        int n = 1_000;

        for(int i=0; i< n; i++){
            queue.enqueue(i);
        }

        assertEquals(n, queue.size());

        for(int i=0; i< n; i++){
            int res = queue.dequeue();
            assertEquals(i, res);
        }

        assertEquals(0, queue.size());
    }

    @Test
    public void testSteps(){

        int n = 1_000;
        int half = n /2;
        int quarter = n/4;

        for(int i=0; i<  half; i++){
            queue.enqueue(i);
        }

        assertEquals(half, queue.size());

        for(int i=0; i< quarter; i++){
            int res = queue.dequeue();
            assertEquals(i, res);
        }

        assertEquals(quarter, queue.size());

        for(int i=half; i< n; i++){
            queue.enqueue(i);
        }

        assertEquals(quarter + half, queue.size());

        for(int i=quarter; i< n; i++){
            int res = queue.dequeue();
            assertEquals(i, res);
        }

        assertEquals(0, queue.size());
    }

}