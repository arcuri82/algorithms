package org.pg4200.les01.array;

import org.pg4200.les01.StringContainerWithIndex;

/**
 * Created by arcuri82 on 14-Aug-17.
 */
public class ArrayStringContainer implements StringContainerWithIndex{

    /**
     * Here we use an array to backup the data we insert in the container.
     */
    private String[] data;

    /**
     * The "size" of the container is not going to be necessarily equal to the length
     * of the "data" array.
     */
    private int size = 0;

    public ArrayStringContainer(){
        //call the other constructor with "10" as default max size.
        this(10);
    }

    public ArrayStringContainer(int maxSize){
        data = new String[maxSize];
    }

    @Override
    public String get(int index) {
        if(index < 0 || index >= size){
            //some input validation
            return null;
        }
        return data[index];
    }

    @Override
    public void add(String value) {
        data[size] = value;
        size++;
    }

    @Override
    public int size() {
        return size;
    }
}
