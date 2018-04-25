package org.pg4200.datastructure.queue;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class LinkedListQueue<T> implements MyQueue<T> {

    private class BiDirectionalNode{
        T value;
        BiDirectionalNode next;
        BiDirectionalNode previous;
    }

    /*
        Note: in contrast to stack in which we only needed the
        tail, here we also need the head
     */
    private BiDirectionalNode head;
    private BiDirectionalNode tail;

    private int size;

    @Override
    public void enqueue(T value) {

        BiDirectionalNode node = new BiDirectionalNode();
        node.value = value;
        size++;

        if(head == null){
            head = node;
            tail = node;
            return;
        }

        /*
            We insert the new element after the tail.
         */
        BiDirectionalNode oldTail = tail;
        tail = node;

        tail.previous = oldTail;
        oldTail.next = tail;
    }

    @Override
    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException();
        }

        T value = head.value;

        if(size == 1){
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }

        size--;

        return value;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException();
        }

        return head.value;
    }

    @Override
    public int size() {
        return size;
    }
}
