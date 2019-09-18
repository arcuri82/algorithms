package org.pg4200.ex01;

class MyArrayListInteger {

    private int[] data;

    private int size = 0;

    MyArrayListInteger(){
        this(10);
    }

    private MyArrayListInteger(int maxSize){
        data = new int[maxSize];
    }


    public int get(int index) {
        if(index < 0 || index >= size){
            return null;
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
