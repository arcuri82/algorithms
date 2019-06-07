package org.pg4200.les04.recursion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 06-Jun-19.
 */
public class RecursiveSumTest {


    private static int sumIterative(int n) {

        int sum = 0;

        for (int i = 1; i <= n; i++) {
            sum += i;
        }

        return sum;
    }

    @Test
    public void testSum() {
        assertEquals(0, RecursiveSumOfN.sumOfAllValues(0));
        assertEquals(1, RecursiveSumOfN.sumOfAllValues(1));
        assertEquals(3, RecursiveSumOfN.sumOfAllValues(2));
        assertEquals(6, RecursiveSumOfN.sumOfAllValues(3));
        assertEquals(10, RecursiveSumOfN.sumOfAllValues(4));
    }

    @Test
    public void testSumComparedToIterative() {

        for (int i = 0; i < 100; i++) {
            assertEquals(sumIterative(i), RecursiveSumOfN.sumOfAllValues(i));
        }
    }

    @Test
    public void testSumNoStopping(){

        /*
            The function will not run forever, as it keeps pushing new frames
            on the function call stack before popping any of them
         */
        assertThrows(StackOverflowError.class, () ->
                RecursiveSumOfN.sumOfAllValuesNoStopping(1)
        );
    }


    @Test
    public void testStackOverflow() {

        int n = 100_000;

        //no problem in running this iterative function, as only 1 frame on the call stack
        sumIterative(n);

        /*
            but we do not have enough space on the call stack for a recursive version,
            as it tries to push n frames before popping any of them
         */

        assertThrows(StackOverflowError.class, () ->
                RecursiveSumOfN.sumOfAllValues(n)
        );
    }



}