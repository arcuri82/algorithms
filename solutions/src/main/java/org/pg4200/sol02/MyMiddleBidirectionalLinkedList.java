package org.pg4200.sol02;

import org.pg4200.les02.list.MyList;

/**
 * Created by arcuri82 on 05-Jun-19.
 */
public class MyMiddleBidirectionalLinkedList<T> implements MyList<T> {

    /*
        Is this better than MyBidirectionalLinkedList?
        As usual, it all depends...
        The get(i) in the 25%-75% range will be faster, but each add/delete becomes
        more expensive, as need to update the middle link
     */

    private class ListNode{
        T value;
        ListNode next;
        ListNode previous;
    }

    private ListNode head;
    private ListNode tail;
    private ListNode middle;
    private int size;


    private int expectedIndexOfMiddle(){
        if(isEmpty()){
            return -1;
        }

        /*
            we do not keep track of the index of middle, as that always
            can be inferred by the size of the list
         */
        return (int) (Math.ceil(size() / 2.0) - 1);
    }

    @Override
    public void delete(int index) {

        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            if(head.next != null){
                head = head.next;
                head.previous = null;
            } else {
                head = null;
                tail = null;
            }
        } else if(index == (size-1)){

            tail.previous.next = null;
            tail = tail.previous;

        } else {

            ListNode target = getNode(index);

            target.previous.next = target.next;
            target.next.previous = target.previous;
        }

        int previous = expectedIndexOfMiddle();
        if(index <= previous){
            previous--;
        }
        size--;
        updateMiddle(previous);
    }

    private void updateMiddle(int previousIndex){

        int current = expectedIndexOfMiddle();
        if(current == previousIndex){
            //nothing to do
            return;
        }

        if(previousIndex < 0){
            //from empty to 1 element
            assert size() == 1;
            middle = head;
        } else if(current < 0){
            //from 1 element to empty
            assert size() == 0;
            middle = null;
        } else if(current < previousIndex){
            //move backwards
            middle = middle.previous;
        } else {
            //move forward
            middle = middle.next;
        }
    }

    private ListNode getNode(int index){

        if(index < 0 || index >= size()){
            throw new IndexOutOfBoundsException();
        }

        ListNode current = null;
        int counter = -1;
        boolean forwards = false;

        if(index <= size() * 0.25){
            //start from head
            current = head;
            counter = 0;
            forwards = true;

        } else if(index > size() * 0.25 && index < size() * 0.75){
            //from middle, either backwards or forwards
            current = middle;
            counter = expectedIndexOfMiddle();
            forwards = index >= size() * 0.5;

        } else {
            //start from tail
           current = tail;
           counter = size -1;
           forwards = false;
        }

        if(forwards){
            while(counter <= index){

                if(counter == index){
                    return current;
                }

                current = current.next;
                counter++;
            }
        } else {
            //backward
            while(counter >= index){

                if(counter ==  index){
                    return current;
                }

                current = current.previous;
                counter--;
            }
        }

        assert false;
        return null;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void add(int index, T value) {

        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }

        ListNode node = new ListNode();
        node.value = value;

        if(head == null){
            head = node;
            tail = node;

        } else if(index == 0){
            head.previous = node;
            node.next = head;
            head = node;

        } else if(index == size) {
            tail.next = node;
            node.previous = tail;
            tail = node;

        } else {

            ListNode target = getNode(index);
            ListNode beforeTarget = target.previous;

            beforeTarget.next = node;
            node.previous = beforeTarget;

            node.next = target;
            target.previous = node;
        }

        int previous = expectedIndexOfMiddle();
        if(index <= previous){
           previous++;
        }
        size++;
        updateMiddle(previous);
    }

    @Override
    public int size() {
        return size;
    }
}
