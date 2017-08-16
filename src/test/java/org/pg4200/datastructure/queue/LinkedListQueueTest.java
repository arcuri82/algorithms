package org.pg4200.datastructure.queue;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class LinkedListQueueTest extends MyQueueTestTemplate{

    @Override
    protected <T> MyQueue<T> getNewInstance(Class<T> klass) {
        return new LinkedListQueue<>();
    }
}