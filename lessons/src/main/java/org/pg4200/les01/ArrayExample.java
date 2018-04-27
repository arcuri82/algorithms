package org.pg4200.les01;

/**
 * Created by arcuri82 on 14-Aug-17.
 */
public class ArrayExample {

    /**
     * Given an array as input, return the sum of all its elements inside.
     *
     * @return 0 if the array is empty
     */
    public static int sum(int[] array){

        /*
            The variable called "array" does not contain any element itself.
            It is actually a pointer (a 64-bit number) to the memory where the data
            is stored.
         */

        if(array == null){
            return 0;
        }

        int sum = 0;

        /*
            How long is the array in input? We do not know, it could
            be anything, and the algorithm should still work regardless.
            So, we need to use "array.length" to get such value.
         */

        for(int i=0; i< array.length; i++){

            /*
               "array[i]" means:
                go to the position in memory with location "array + i",
                read the next 4 bytes, and return those interpreted as
                a "int" value.

                IMPORTANT: in Java you cannot manipulate object pointers
                directly, eg "array + i" will give you a compilation error.
                However, other languages (eg C/C++) allow it.
             */

            sum += array[i];
        }

        return sum;
    }

}
