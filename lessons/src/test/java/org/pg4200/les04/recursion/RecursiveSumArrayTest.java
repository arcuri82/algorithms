package org.pg4200.les04.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class RecursiveSumArrayTest {

    private static int sumIterative(int[] array){

        int sum = 0;

        for(int value : array){
            sum += value;
        }

        return sum;
    }


    @Test
    public void testCompare(){

        int[] array = {5, 2, 7, -4, 3, 1, 1, 2, 1234, -56, 5, 3, 4};
        int expected = sumIterative(array);

        int single = RecursiveSumArray.sumSingleCall(array);
        int two = RecursiveSumArray.sumTwoCalls(array);

        assertEquals(expected, single);
        assertEquals(expected, two);
    }


    @Test
    public void testLargeArray(){

        //large array, but containing only 0s, which is default value
        int[] array = new int[100_000];
        array[1234] = 234325;

        int expected = sumIterative(array);

        //can't handle with single-call recursion
        assertThrows(StackOverflowError.class, () ->
                RecursiveSumArray.sumSingleCall(array))
        ;

        /*
            as the log2(100_000)=16.6, no problems with two-call recursion in which
            input is halved at each call
         */
        int two = RecursiveSumArray.sumTwoCalls(array);


        assertEquals(expected, two);
    }
}