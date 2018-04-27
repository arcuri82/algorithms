package org.pg4200.les01;

import org.junit.jupiter.api.Test;
import org.pg4200.les01.ArrayExample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 14-Aug-17.
 */

public class ArrayExampleTest {

    @Test
    public void testBase(){

        //values initialized when array is created
        int[] array = {1, 2, 3};

        int res = ArrayExample.sum(array);

        assertEquals(6, res);
    }

    @Test
    public void testNegative(){

        //first create array, then populate it
        int[] array = new int[3];
        array[0] = -2;
        array[1] = -1;
        //array[2] = 0; // default is 0

        int res = ArrayExample.sum(array);

        assertEquals(-3, res);
    }

    @Test
    public void testLarge(){

        int x = 1_000_000_000; //1 billion

        int[] array = {x, x, x};

        int res = ArrayExample.sum(array);

        /*
            Why the sum of 3 positive values end up becoming negative?
         */
        assertTrue(res < 0);

        /*
            "int" in Java are signed 32 bits, which means highest value is (2^31)-1 ~= 2 billions.
            So end up with so called "overflow"
         */
    }
}
