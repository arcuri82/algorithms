package org.pg4200.les03.sort;

// WARNING: this is one of the 12 classes you need to study and know by heart



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

            /*
                At each step, the elements before "i" are sorted,
                but "i" itself might be of lower value.
                In such latter case, the value at "i" need to be inserted
                in the right order between the previous ones.
                This is done by comparing two elements at a time,
                from right to left, until the element is greater than
                what is on its left-side, ie no more swapping is necessary.
             */

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
