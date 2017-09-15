package org.pg4200.datastructure.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by arcuri82 on 15-Sep-17.
 */
public class IterableLinkedList<T> implements Iterable<T> {

    private class BiDirectionalNode {
        T value;
        BiDirectionalNode next;
        BiDirectionalNode previous;
    }

    private BiDirectionalNode head;
    private BiDirectionalNode tail;

    private int size;

    private int modificationCounter = 0;

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int initialCounter = modificationCounter;

            private BiDirectionalNode next = head;

            @Override
            public boolean hasNext() {
                checkModifications();
                return next != null;
            }

            @Override
            public T next() {
                checkModifications();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                BiDirectionalNode res = next;
                next = next.next;
                return res.value;
            }

            private void checkModifications() {
                if (initialCounter != modificationCounter) {
                    throw new IllegalStateException();
                }
            }
        };
    }


    public void delete(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }


        if (index == 0) {
            if (head.next != null) {
                head = head.next;
                head.previous = null;
            } else {
                head = null;
                tail = null;
            }
        } else {

            BiDirectionalNode current = head;
            int counter = 0;
            while (counter != index - 1) {
                current = current.next;
                counter++;
            }

            if (current.next == tail) {
                //we are trying to delete the tail, so we need to update it
                tail = current;
            }

            current.next = current.next.next;
            if(current.next != null) {
                current.next.previous = current;
            }
        }

        size--;
        modificationCounter++;
    }

    public T get(int index) {

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }


        BiDirectionalNode current = head;
        int counter = 0;

        while (current != null) {

            if (counter == index) {
                return current.value;
            }

            current = current.next;
            counter++;
        }

        assert false;
        return null;
    }

    public void add(T value) {

        BiDirectionalNode node = new BiDirectionalNode();
        node.value = value;
        size++;
        modificationCounter++;

        if (head == null) {
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        node.previous = tail;
        tail = node;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size <= 0;
    }
}
