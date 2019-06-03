package org.pg4200.les01.linkedlist;

import org.pg4200.les01.MyListString;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyNaiveLinkedListString implements MyListString {

    /*
        For each "value" in the container, we create a Node object to contain it.
        Each node object will have a pointer to the next one in the list, apart
        from the last one (which will have "null").

        Because this is just an internal detail of the container, and it
        is of no use out of it, this class is declared 'private' here.
        This also means that we can access its fields directly instead of
        using getters/setters, as this class can only be used here, so
        no need to worry about OO encapsulation.
     */
    private class ListNode{
        String value;
        ListNode next;
    }

    /**
     * The first element in the list
     */
    private ListNode head;


    @Override
    public String get(int index) {

        if(head == null){
            return null;
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

        return null;
    }

    @Override
    public void add(String value) {

        ListNode node = new ListNode();
        node.value = value;

        if(head == null){
            //empty container
            head = node;
            return;
        }

        //go to the last element
        ListNode current = head;
        while(current.next != null){
            current = current.next;
        }

        current.next = node;
    }

    @Override
    public int size() {

        if(head == null){
            return 0;
        }

        int size = 1;

        //go to the last element
        ListNode current = head;
        while(current.next != null){
            current = current.next;
            size++;
        }

        return size;
    }

    /*
        Why did I call this implementation "naive"?
        Because it is very inefficient: each operation "might" need to traverse
        the entire list.
        So, if you have 1 million elements, it will take much longer than just
        using a backing array.
        However, one advantage here is that we can add as many elements as we want,
        as long as there is enough RAM for it.
     */
}
