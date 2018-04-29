package org.pg4200.les04;

import org.pg4200.les03.MySort;

public class InsertionSort implements MySort {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {

        if (array == null) {
            return;
        }

        /*
            We start from 1 (2nd element), as an array of length 0
            or 1 is sorted by definition
         */
        for(int i=1; i<array.length; i++){

            for(int j=i-1; j>=0; j--){
                if (array[j].compareTo(array[j+1]) > 0) {
                    T tmp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = tmp;
                } else {
                    break;
                }
            }
        }
    }
}
