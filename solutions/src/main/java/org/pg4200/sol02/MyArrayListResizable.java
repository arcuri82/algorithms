package org.pg4200.sol02;

import org.pg4200.les02.list.MyArrayList;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyArrayListResizable<T> extends MyArrayList<T> {

    public MyArrayListResizable(){
        super();
    }

    public MyArrayListResizable(int capacity){
        super(capacity);
    }

    @Override
    public void add(int index, T value) {

        if(size == data.length){
            /*
                Array is full, need to create new, bigger one.
                Note: for simplicity I am not adding code to check
                if new size does not overflow.
                If you want to see how to do it properly, look at the source
                code of java.lang.ArrayList
             */

            Object[] bigger = new Object[data.length * 2];
            for(int i=0; i<data.length; i++){
                bigger[i] = data[i];
            }
            data = bigger;
        }

        /*
            No need to copy&paste code (which would be bad!).
            Once data buffer is resized, just call the original code
            in the parent/super class.
         */
        super.add(index, value);
    }
}
