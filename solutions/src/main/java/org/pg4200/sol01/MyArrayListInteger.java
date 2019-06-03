package org.pg4200.sol01;

public class MyArrayListInteger {

    private Integer[] data;

    private int size = 0;

    public MyArrayListInteger(){
        this(10);
    }

    public MyArrayListInteger(int maxSize){
        data = new Integer[maxSize];
    }

    public Integer get(int index) {
        if(index < 0 || index >= size){
            //some input validation
            return null;
        }
        return data[index];
    }

    public void add(Integer value) {
        data[size] = value;
        size++;
    }

    public int size() {
        return size;
    }

}
