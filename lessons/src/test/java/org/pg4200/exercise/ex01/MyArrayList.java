package org.pg4200.exercise.ex01;

import org.pg4200.datastructure.delete.ArrayDeleteContainer;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyArrayList<T> extends ArrayDeleteContainer<T> {

    public MyArrayList(){
        super();
    }

    public MyArrayList(int capacity){
        super(capacity);
    }

    @Override
    public void add(T value) {

        if(size == data.length){
            /*
                array is full, need to create new, bigger one.
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

        super.add(value);
    }
}
