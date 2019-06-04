package org.pg4200.les01.linkedlist;

import org.pg4200.les01.MyListString;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyLinkedListString implements MyListString {

    /*
        For each "value" in the container, we create a Node object to contain it.
        Each node object will have a pointer to the next one in the list, apart
        from the last one (which will have "null")
     */
    private class ListNode{
        String value;
        ListNode next;
    }

    /**
     * The first element in the list
     */
    private ListNode head;

    /**
     *  The last element in the list
     */
    private ListNode tail;

    /**
     *  The number of elements contained in this list
     */
    private int size;

    /**
        Note: this constructor declaration here is redundant, as
        I am assigning default values anyway
     */
    public MyLinkedListString(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String get(int index) {

        /*
            Here, we still have to traverse the list from the "head"
         */

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
        size++;

        if(head == null){
            /*
                Empty container.
                The newly created element is both the first and the
                last in the list.
             */
            head = node;
            tail = node;
            return;
        }

        tail.next = node;
        tail = node;

        /*
            Here we did modify the the "tail", but not the "head". Why?
            Wouldn't "head.next" always be null, and so the list will have
            at most 1 element?

            The point is that you need to remember that object references are just
            pointers to location in memory.
            So, after the first element has been added, before we add a new "node" we have

            Memory | Variable | Pointer to Heap
            x      | head     | k
            y      | tail     | k
            z      | node     | q

            Memory  | Next
            k       | null
            q       | null


            The operation on "tail.next = node" would NOT change the current
            values of the references in this method, as no head/tail/node is modified.
            The heap is modified though into:

            Memory  | Next
            k       | q
            q       | null

            ie, "tail.next" means go to the object in the heap at position "k" and, there,
            with "=node" replace the value of the variable "next" with the pointer of the
            object "node" (which we can see that points to "q").

            Note, however, that both "head" and "tail" are still both pointing to the same "k".
            So, "head.next" is not null, but pointing to "q"

            The final step "tail = node" does the last change on the stack, so finally:

            Memory | Variable | Pointer to Heap
            x      | head     | k
            y      | tail     | q
            z      | node     | q

            Memory  | Next
            k       | q
            q       | null

            After this method is completed, the reference to "node" is discarded, as it lived
            on the method call stack trace, ie the slot at "z" is freed and can be recycled.
         */
    }

    @Override
    public int size() {

        return size;
    }

    /*
        How does this implementation differ from the "naive" one?
        Now, both "size()"  and "add()" are much quicker, as they do
        not need "while" loops to iterate over all the elements starting
        from the head.
        The "get()" is still the same though.
     */
}
