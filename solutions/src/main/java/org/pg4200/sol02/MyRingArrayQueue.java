package org.pg4200.sol02;

import org.pg4200.les02.queue.MyQueue;

/**
 * Created by arcuri82 on 16-Aug-17.
 */
public class MyRingArrayQueue<T> implements MyQueue<T>{

    protected Object[] data;

    /*
        Note: here it can happen that tail < head
     */

    private int head = -1;
    private int tail = -1;

    public MyRingArrayQueue(){
        this(10);
    }

    public MyRingArrayQueue(int capacity){
        data = new Object[capacity];
    }

    @Override
    public void enqueue(T value) {

        if(isEmpty()){
            head = 0;
            tail = 0;
        } else if(head <= tail){

            if(tail < data.length - 1) {
                //simple case
                tail++;
            } else {
                //ring: start from the beginning, but only if space available
                if(head != 0){
                    tail = 0;
                } else {
                    //opss, array is totally full, so let's make a copy in a bigger one
                    Object[] tmp = new Object[data.length * 2];

                    for(int i=0; i<data.length; i++){
                        tmp[i] = data[i];
                    }
                    data = tmp;
                    tail++;
                }
            }
        } else {
            assert tail < head;

            if(tail < head - 1){
                // still space available
                tail++;
            } else {
                /*
                    array is totally full. but making copy is not trivial,
                    as we need to make sure to re-align such that head=0 && head < tail
                 */
                Object[] tmp = new Object[data.length * 2];

                int k = data.length - head;
                for(int i=0; i<k; i++){
                    tmp[i] = data[head + i];
                }

                for(int i=0; i< (tail+1); i++){
                    tmp[k + i] = data[i];
                }

                head = 0;
                tail = data.length;
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
            if(head >= data.length){
                head = 0;
            }
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
        } else if(head == tail) {
            return 1;
        } else if(head < tail) {
            //normal case
            return (tail - head) + 1;
        } else {
            int size = 0;
            //add size based on all elements after head
            size += (data.length - head);
            //then add all from 0 til tail
            size += tail + 1;

            return size;
        }
    }
}
