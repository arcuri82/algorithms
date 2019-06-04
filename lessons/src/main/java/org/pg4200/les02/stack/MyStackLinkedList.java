package org.pg4200.les02.stack;

// WARNING: this is one of the 12 classes you need to study and know by heart


public class MyStackLinkedList<T> implements MyStack<T>{

    private class StackNode {
        T value;
        StackNode previous;
    }

    /*
        We could extend MyLinkedList (which would had been fine).
        But, for a stack, we don't need a head.
        So, here we just implement it directly.
     */
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
