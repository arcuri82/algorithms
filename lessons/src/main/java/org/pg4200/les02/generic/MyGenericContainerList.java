package org.pg4200.les02.generic;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyGenericContainerList<T> implements MyGenericContainer<T> {

    private class ListNode{
        T value;
        ListNode next;
    }

    private ListNode head;
    private ListNode tail;
    private int size;

    @Override
    public void delete(int index) {

        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }


        if(index == 0){
            if(head.next != null){
                //the new head
                head = head.next;
            } else {
                //only one element in the collection, which now becomes empty
                head = null;
                tail = null;
            }
        } else {

            ListNode current = head;
            int counter = 0;
            while(counter != index - 1){
                current = current.next;
                counter++;
            }
            //at this point, "current" point to the node BEFORE the one we want to delete

            if(current.next == tail){
                //we are trying to delete the tail, so we need to update it
                tail = current;
            }

            current.next = current.next.next;

            /*
                The line above could look strange...

                A -> B -> C

                here we want to remove B, where "current=A",
                and so we want to end up with

                A -> C

                B is the next of A, where C is the next of B.
                The next of B is also the "next next" of A.
             */
        }

        size--;
    }

    /*
        Is the above implementation of "delete()" better or worse than in
        ArrayDeleteContainer?
        It depends...
        If we delete the first element, the list is very quick, whereas with
        array we need to shift left the entire collection.
        On the other hand, if we delete the last, there is no shift with array,
        but in list we need to traverse the entire collection, even if we do
        actually have a pointer to "tail".
     */

    @Override
    public T get(int index) {

        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }

        //need to traverse all the nodes until the "index"th one.

        ListNode current = head;
        int counter = 0;

        while(current != null){

            if(counter == index){
                //found it
                return current.value;
            }

            //go to the next element
            current = current.next;
            counter++;
        }

        /*
            We should never reach this code, unless bug.
            But we still need to return null, otherwise the
            compiler complains
          */
        assert false;
        return null;
    }

    @Override
    public void add(T value) {

        ListNode node = new ListNode();
        node.value = value;
        size++;

        if(head == null){
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        tail = node;
    }

    @Override
    public int size() {
        return size;
    }
}
