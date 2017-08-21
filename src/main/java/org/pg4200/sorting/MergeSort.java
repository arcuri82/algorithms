package org.pg4200.sorting;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class MergeSort implements MySort{


    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if(array == null){
            return;
        }

        T[] buffer = (T[]) new Comparable[array.length];

        mergesort(0, array.length -1, array, buffer);
    }

    private <T extends Comparable<T>> void mergesort(int low, int high, T[] array, T[] buffer) {

        if (low < high) {

            int middle = low + (high - low) / 2;

            mergesort(low, middle, array, buffer);

            mergesort(middle + 1, high, array, buffer);

            merge(low, middle, high, array, buffer);
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
