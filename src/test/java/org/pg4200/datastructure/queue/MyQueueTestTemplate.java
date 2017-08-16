package org.pg4200.datastructure.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public abstract class MyQueueTestTemplate {

    protected abstract <T> MyQueue<T> getNewInstance(Class<T> klass);

    private MyQueue<Integer> queue;

    @Before
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

        try{
            queue.dequeue();
            fail();
        } catch (RuntimeException e){
            //expected
        }
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
        Note: the following 2 tests are meant to test the internal
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

        for(int i=0; i< 1_000; i++){
            queue.enqueue(i);
            queue.enqueue(i);
            queue.dequeue();
            //ie, insert 2 elements, and then remove 1
        }

        assertEquals(n, queue.size());
        assertEquals(n/2, queue.peek().intValue());
    }
}