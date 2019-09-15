package org.pg4200.les02.list;

// WARNING: this is one of the 12 classes you need to study and know by heart


/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyLinkedList<T> implements MyList<T> {

    /*
        For each "value" in the container, we create a Node object to contain it.
        Each node object will have a pointer to the next one in the list, apart
        from the last one (which will have "null")
     */
    private class ListNode{
        T value;
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
     *  The number of elements contained in this container
     */
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
            /*
                after that while-loop, the "current" variable does point to the node BEFORE the one we want to
                delete, as the loop has condition "counter != index - 1".
                Assume for example you want to delete the element at position index=4.
                The element before it is index=3. So, the "current" variable will point the element
                which is at position 3, and not 4. I.e., "current == list.get(3)".
                To delete an element, we need to remove/modify the "next" link from the previous node in the list
             */

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
        MyArrayList?
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
    public void add(int index, T value) {

        if(index < 0 || index > size){
            //note that here "size" is a valid index
            throw new IndexOutOfBoundsException();
        }

        ListNode node = new ListNode();
        node.value = value;

        if(head == null){
            //add on empty list
            assert size == 0;
            head = node;
            tail = node;

        } else if(index == 0){
            //add at beginning of non-empty list
            node.next = head;
            head = node;

        } else if(index == size) {
            /*
                Add at the end of non-empty list.
                Note: using "tail" allows us for an efficient implementation
                of "add(value)", as we do not need to traverse the whole list
                to append at the end.
             */
            tail.next = node;
            tail = node;

        } else {
            //insertion in the middle of the list
            int counter = 0;
            ListNode previous = head;

            while(counter != index - 1){
                previous = previous.next;
                counter++;
            }

            node.next = previous.next;
            previous.next = node;
            /*
                We are in the case of

                ... -> A -> B -> ...

                and we want to insert X at the position of B, resulting in

                ... -> A -> X -> B -> ...

                this means that A.next will have to point to X,
                whereas X.next would be B. We do not need to modify B.
                As we need to iterate from the head, we stop at A, as we need
                to change its A.next value.
                We do not have B if we stop at A. But B can be read by simply
                noticing that B == A.next.
                Therefore, we can iterate with a "previous" variable until we reach
                A, which would be at position index-1.
             */
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }
}
