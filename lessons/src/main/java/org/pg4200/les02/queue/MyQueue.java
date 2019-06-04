package org.pg4200.les02.queue;

/**
 * Queues are FIFO: First in, First out
 *
 * Note: "queue" (British English) is the same as "line" (American English)
 *
 * Created by arcuri82 on 16-Aug-17.
 */
public interface MyQueue<T> {

    /**
     * Insert a new element at the tail of the queue/line
     */
    void enqueue(T value);

    /**
     *  Remove and return the element at the head of the queue/line
     */
    T dequeue();

    /**
     * Look at the head of the queue/line, without removing it
     */
    T peek();

    int size();

    default boolean isEmpty(){
        return size() == 0;
    }
}
