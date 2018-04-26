package org.pg4200.datastructure.queue;

import org.pg4200.les02.queue.ArrayQueue;
import org.pg4200.les02.queue.MyQueue;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class ArrayQueueTest extends MyQueueTestTemplate{

    @Override
    protected <T> MyQueue<T> getNewInstance(Class<T> klass) {
        return new ArrayQueue<>();
    }
}