package org.pg4200.datastructure.stack;



public class LinkedListStack<T> implements MyStack<T>{

    private class BiDirectionalNode{
        T value;
        BiDirectionalNode next;
        BiDirectionalNode previous;
    }

    //Note: for a stack, we don't need a head
    private BiDirectionalNode tail;

    private int size;

    @Override
    public void push(T value) {

        BiDirectionalNode node = new BiDirectionalNode();
        node.value = value;
        size++;

        if(tail == null){
            //first element to insert in empty list
            tail = node;
            return;
        }

        tail.next = node;
        node.previous = tail;
        tail = node;
    }

    @Override
    public T peek() {
        if(tail == null){
            throw new RuntimeException();
        }

        return tail.value;
    }

    @Override
    public T pop() {
        if(tail == null){
            throw new RuntimeException();
        }

        T value = tail.value;
        tail = tail.previous;

        if(tail != null) {
            tail.next = null;
        }

        size--;

        return value;
    }

    @Override
    public int size() {
        return size;
    }
}
