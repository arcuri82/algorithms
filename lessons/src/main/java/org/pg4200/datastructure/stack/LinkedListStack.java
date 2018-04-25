package org.pg4200.datastructure.stack;



public class LinkedListStack<T> implements MyStack<T>{

    private class StackNode {
        T value;
        StackNode previous;
    }

    //Note: for a stack, we don't need a head
    private StackNode tail;

    private int size;

    @Override
    public void push(T value) {

        StackNode node = new StackNode();
        node.value = value;
        size++;

        if(tail == null){
            //first element to insert in empty list
            tail = node;
            return;
        }

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

        size--;

        return value;
    }

    @Override
    public int size() {
        return size;
    }
}
