package org.pg4200.sol03;

import java.util.Comparator;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class OptimizedBubbleSort {


    public <T> int sort(T[] array, Comparator<T> comparator, boolean optimized) {

        if (array == null) {
            return 0;
        }

        int counter = 0;

        //make sure we enter into first loop of the "while"
        boolean swapped = true;
        int lastSwap = array.length-1;

        while (swapped) {

            /*
                if there is not going to be any swapping, then the
                array is sorted, and we do not need to recheck
             */

            swapped = false;

            int limit = array.length - 1;

            if(optimized){
                limit = lastSwap;
            }

            for (int i = 0; i < limit; i++) {
                int j = i + 1;
                /*
                    if current element is greater than next,
                    then swap them.
                    Like a bubble, the highest value will fly up.
                 */

                counter++;

                if (comparator.compare(array[i], array[j]) > 0) {
                    T tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                    swapped = true;
                    lastSwap = i;
                }
            }
        }

        return counter;
    }
}
