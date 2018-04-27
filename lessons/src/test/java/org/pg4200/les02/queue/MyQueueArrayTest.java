package org.pg4200.les02.queue;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class MyQueueArrayTest extends MyQueueTestTemplate{

    @Override
    protected <T> MyQueue<T> getNewInstance(Class<T> klass) {
        return new MyQueueArray<>();
    }
}