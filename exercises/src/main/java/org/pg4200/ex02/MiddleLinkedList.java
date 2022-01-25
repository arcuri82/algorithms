package org.pg4200.ex02;

import org.pg4200.les02.list.MyList;

import java.util.List;

public class MiddleLinkedList<T> implements MyList<T> {

    private class ListNode{
        T value;
        ListNode next;
        ListNode previous;
    }

    private ListNode start;
    private ListNode end;
    private ListNode middle;
    int size;

    @Override
    public void delete(int index) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    private ListNode getNode(int index) {

        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        if( index <= size/4 ){
            ListNode currentNode = start;
            int counter = 0;

            while(currentNode != null){

                if(counter == index){
                    return currentNode;
                }

                currentNode = currentNode.next;
                counter++;
            }
        } else if (index < size / 2){
            ListNode currentNode = middle;
            int counter = size / 2;

            while(currentNode != null){

                if(counter == ((size-1) - index)){
                    return currentNode;
                }

                currentNode = currentNode.previous;
                counter++;
            }
        } else if(index < 3 * size/ 4) {
            ListNode currentNode = middle;
            int counter = size / 2;

            while (currentNode != null){
                if(counter == index){
                    return currentNode;
                }
                currentNode = currentNode.next;
                counter++;
            }
        } else {
            ListNode currentNode = end;
            int counter = 0;
            while (currentNode!= null){
                if(counter == (size-1) - index){
                    return currentNode;
                }
                currentNode = currentNode.previous;
                counter++;
            }
        }

        return null;
    }

    @Override
    public void add(T value) {
        MyList.super.add(value);
    }

    @Override
    public void add(int index, T value) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        ListNode insertedNode = new ListNode();
        insertedNode.value = value;

        if(start == null){
            start = insertedNode;
            middle = insertedNode;
            end = insertedNode;
            size++;
        } else if (index == 0){
            start.previous = insertedNode;
            insertedNode.next = start;
            start = insertedNode;

            // what happens to the middle.
            size++;
            if(size%2 != 0){
                middle = middle.previous;
            }
        } else if(index == size){
            end.next = insertedNode;
            insertedNode.previous = end;
            end = insertedNode;
            size++;
            if(size%2 != 0){
                middle = middle.next;
            }
        } else {
            ListNode target = getNode(index);
            ListNode beforeTarget = target.previous;

            beforeTarget.next = insertedNode;
            insertedNode.previous = beforeTarget;

            target.previous = insertedNode;
            insertedNode.next = target;

            size++;

            if(index < size/2 && size%2 != 0){
                middle = middle.previous;
            }else if (index > size/2 && size%2!= 0){
                middle = middle.next;
            }
        }


    }

    @Override
    public int size() {
        return 0;
    }
}
