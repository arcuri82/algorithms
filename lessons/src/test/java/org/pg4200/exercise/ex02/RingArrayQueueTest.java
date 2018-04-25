package org.pg4200.exercise.ex02;

import org.junit.Test;
import org.pg4200.datastructure.queue.MyQueue;
import org.pg4200.datastructure.queue.MyQueueTestTemplate;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class RingArrayQueueTest extends MyQueueTestTemplate{

    @Override
    protected <T> MyQueue<T> getNewInstance(Class<T> klass) {
        return new RingArrayQueue<>();
    }

    @Test
    public void testFailToPeekOnEmpty(){

        try{
            queue.peek();
            fail();
        } catch (RuntimeException e){
            //expected
        }
    }
}