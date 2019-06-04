package org.pg4200.les02.queue;

import org.pg4200.les02.list.MyLinkedList;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class MyQueueLinkedList<T> extends MyLinkedList<T> implements MyQueue<T> {

    /*
        Here, we can simply extend MyLinkedList, and define the queue methods using it.
        This is not inefficient, in contrast to MyArrayList which would have awful performance.
     */

    @Override
    public void enqueue(T value) {
        add(value);
    }

    @Override
    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException();
        }

        T value = get(0);
        delete(0);

        return value;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException();
        }

        return get(0);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
