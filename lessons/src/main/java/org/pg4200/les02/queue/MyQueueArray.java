package org.pg4200.les02.queue;

// WARNING: this is one of the 12 classes you need to study and know by heart


/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class MyQueueArray<T> implements MyQueue<T>{

    /*
        Here, we shouldn't just extend MyArrayList.
        It would work, but it would be very inefficient, as each
        dequeue operation would always force a full left-shift of the whole
        array-list (recall that a dequeue would be equivalent to a delete at
        index 0 in the array-list).
     */

    protected Object[] data;

    /*
        When the queue is empty, we need that the "head"
        and "tail" represent invalid values.
        If they were objects, we could use "null".
        But if we use "int", then we need to use something
        equivalent to "null", as primitive values (int, double,
        boolean, etc.) do not have null.
        So, an option is  to just represent invalid values with
        negative int, because anyway negative values would never
        be valid indices in an array.

        Note: another option would be to use Integer instead of int,
        as being an object it has null. However, being Integer just
        a wrapper around int, using them as indices would not
        be efficient.
     */

    private int head = -1;
    private int tail = -1;

    /*
        Note: here we do not need to have a variable for "size" anymore,
        as we can infer it from "head" and "tail"
     */


    public MyQueueArray(){
        this(10);
    }

    public MyQueueArray(int capacity){
        data = new Object[capacity];
    }

    @Override
    public void enqueue(T value) {

        if(isEmpty()){
            head = 0;
            tail = 0;
        } else if(tail < data.length - 1){
            //there is space
            tail++;
        } else {
            /*
                we run out of space, where "tail"
                points to the last element in the array.
                What to do?
                Here we consider 2 options, based on the
                position of "head".
             */
            if(size() < data.length / 5){
                /*
                    not so many elements in the array, so
                    just shift them to the left, in a way
                    to align the "head" at the beginning of
                    the array.

                    Note: the choice of "5" is arbitrary...
                 */
                int  size = size();
                for(int i=0; i<size; i++){
                    data[i] = data[i + head];
                }
                head = 0;
                tail = size;
            } else {
                //too many elements... let's just create a new array with double size
                Object[] tmp = new Object[data.length * 2];

                int  size = size();
                for(int i=0; i<size; i++){
                    tmp[i] = data[i + head];
                }
                head = 0;
                tail = size;
                data = tmp;
            }
        }

        data[tail] = value;
    }

    @Override
    public T dequeue() {

        if(isEmpty()){
            throw new RuntimeException();
        }

        T value = (T) data[head];

        if(size() == 1){
            //now it ll be empty
            head = -1;
            tail = -1;
        } else {
            head++;
        }

        return value;
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException();
        }

        return (T) data[head];
    }

    @Override
    public int size() {

        if(head < 0){
            return 0;
        }

        return (tail - head ) + 1;
    }
}
