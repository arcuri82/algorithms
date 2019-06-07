package org.pg4200.les04.recursion;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class RecursiveSumArray {

    public static int sumSingleCall(int[] array){
        return sumSingleCall(array, 0, array.length-1);
    }

    private static int sumSingleCall(int[] array, int start, int end){

        if(start == end){
            //only 1 value
            return array[start];
        }

        if(start > end){
            //invalid indices
            return 0;
        }

        /*
            to sum all values between start and end, we can take the current
            array[start] value and add it the sum of start+1 till end
         */
        return array[start] + sumSingleCall(array, start+1, end);
    }


    public static int sumTwoCalls(int[] array){
        return sumTwoCalls(array, 0, array.length-1);
    }

    private static int sumTwoCalls(int[] array, int start, int end){

        if(start == end){
            //only 1 value
            return array[start];
        }

        if(start > end){
            //invalid indices
            return 0;
        }

        int middle = (int)((double) (start + end) / 2d);

        /*
            The sum of all values in an array is equal to the sum in its left-half
            plus the sum in its right-half
         */
        return sumTwoCalls(array, start, middle) + sumTwoCalls(array, middle + 1, end);
    }
}
