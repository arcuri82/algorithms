package org.pg4200.les04.sort;

// WARNING: this is one of the 12 classes you need to study and know by heart


import org.pg4200.les03.sort.MySort;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class MergeSort implements MySort {


    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if (array == null) {
            return;
        }

        /*
            Here we create a buffer as a local variable, and pass it
            around at each recursive mergesort(...) call.
            It might be tempting to rather put it in a field, eg
            "private T[] buffer;"
            to avoid having to pass it around at each call.
            However, that would not be "thread-safe", ie two (or more)
            threads trying to sort different arrays at the same time using
            the same MergeSort object would
            end-up overriding such same buffer, leading to bugs.
            Multi-threading is something we do not see in details in this
            course, though.
            Furthermore, you should consider that usually a sorting algorithm
            would not need for an object instance, and so could be in a "static"
            method. Here we use regular methods to be able to ovverride from an
            interface (so we can re-use the same test cases for all the different
            implementations of MySort; recall you cannot override static methods).
            When using static methods, could still have something like:
            "private static T[] buffer;"
            which would lead to those mentioned multi-threading issues.

            Note that, besides the "buffer" array, also the input "array" array
            could be saved in a private field. But that would lead to exactly the
            same problem with multi-threading.
         */
        T[] buffer = (T[]) new Comparable[array.length];

        mergesort(0, array.length - 1, array, buffer);
    }

    private <T extends Comparable<T>> void mergesort(int low, int high, T[] array, T[] buffer) {

        if (low >= high) {
            /*
                This means we are in a subarea of array with 1 or less elements.
                As such subarray is sorted by definition (ie less than 2 elements),
                we do not need to do anything
             */
            return;
        }

        int middle = low + (high - low) / 2;

        mergesort(low, middle, array, buffer);

        mergesort(middle + 1, high, array, buffer);

        merge(low, middle, high, array, buffer);
    }

    private <T extends Comparable<T>> void merge(int low, int middle, int high, T[] array, T[] buffer) {

        for (int i = low; i <= high; i++) {
            buffer[i] = array[i];
        }

        //index over the left half, before middle
        int i = low;

        //index over the right half, after middle
        int j = middle + 1;

        for (int k = low; k <= high; k++) {
            if (i > middle) {
                //done with left half, just copy over the right
                array[k] = buffer[j++];
            } else if (j > high) {
                //done with right half, just copy over the left
                array[k] = buffer[i++];
            } else if (buffer[j].compareTo(buffer[i]) < 0) {
                array[k] = buffer[j++];
            } else {
                array[k] = buffer[i++];
            }
        }
    }
}
