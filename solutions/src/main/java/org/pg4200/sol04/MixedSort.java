package org.pg4200.sol04;

import org.pg4200.les03.sort.MySort;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class MixedSort implements MySort{

    private final int bubbleLimit;

    public MixedSort(int bubbleLimit) {
        this.bubbleLimit = bubbleLimit;
    }

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if(array == null){
            return;
        }

        T[] buffer = (T[]) new Comparable[array.length];

        mergesort(0, array.length -1, array, buffer);
    }

    private <T extends Comparable<T>> void mergesort(int low, int high, T[] array, T[] buffer) {

        /*
            MergeSort has some overheads, like calling itself recursively and copying over
            data to the temporary buffer.
            For small arrays (eg length 4), bubble sort could be faster, as not having
            those overheads, even though asymptotically it is worse than merge sort.
            But, for a fixed small size, then asymptotic properties are not so important.
            However, to check if indeed you need any gain, you need to run some benchmark.
         */
        if(high - low < bubbleLimit){
            bubbleSort(low, high,array);
            return;
        }

        if (low < high) {

            int middle = low + (high - low) / 2;

            mergesort(low, middle, array, buffer);

            mergesort(middle + 1, high, array, buffer);

            merge(low, middle, high, array, buffer);
        }
    }

    private <T extends Comparable<T>> void bubbleSort(int low, int high, T[] array) {


        boolean swapped = true;
        int lastSwap = high;

        while (swapped) {

            swapped = false;
            int limit = lastSwap;

            for (int i = low; i < limit; i++) {
                int j = i + 1;


                if (array[i].compareTo(array[j]) > 0) {
                    T tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                    swapped = true;
                    lastSwap = i;
                }
            }
        }
    }

    private <T extends Comparable<T>> void merge(int low, int middle, int high, T[] array, T[] buffer) {

        for (int i = low; i <= high; i++) {
            buffer[i] = array[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high) {
            if (buffer[i].compareTo(buffer[j]) <= 0) {
                array[k] = buffer[i];
                i++;
            } else {
                array[k] = buffer[j];
                j++;
            }
            k++;
        }

        while (i <= middle) {
            array[k] = buffer[i];
            k++;
            i++;
        }
    }
}
