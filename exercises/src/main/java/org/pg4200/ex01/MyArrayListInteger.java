package org.pg4200.ex01;

public class MyArrayListInteger {

    /**
     * Here we use an array to backup the data we insert in the container.
     */
    private int[] data;

    /**
     * The "size" of the container is not going to be necessarily equal to the length
     * of the "data" array.
     */
    private int size = 0;

    public MyArrayListInteger(){
        //call the other constructor with "10" as default max size.
        this(10);
    }

    private MyArrayListInteger(int maxSize){
        data = new int[maxSize];
    }


    public int get(int index) {
        if(index < 0 || index >= size){
            //some input validation
            return size;
        }
        return data[index];
    }


    public void add(int value) {
        data[size] = value;
        size++;
    }


    public int size() {
        return size;
    }
}
