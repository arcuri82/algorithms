package org.pg4200.les06.hash;


import org.junit.jupiter.api.Test;
import org.pg4200.les06.hash.HashFunctions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-Sep-17.
 */
public class HashFunctionsTest {


    @Test
    public void testHashLongToInt(){

        assertEquals(0, HashFunctions.hashLongToInt(0));
        assertEquals(42, HashFunctions.hashLongToInt(42));

        assertEquals(0, HashFunctions.hashLongToInt(0x1_00_00_00_00L));
        assertEquals(5, HashFunctions.hashLongToInt(0x7_00_00_00_05L));
        assertEquals(0, HashFunctions.hashLongToInt(0xFF_FF_00_00_00_00L));
    }

    @Test
    public void testHashLongToIntNegative(){

        /*
            A long with the last 32 bits being all 1s
         */
        long one32times = 0xFF_FF_FF_FFL;
        assertTrue(one32times > 2_000_000_000); // at least 2 billions
        /*
            The fact that this turns into a -1 might be confusing at first...
            however the first bit is for the sign. being 1, it means that the
            int is going to be negative.
            as int is represented with as "two's complement", all 1s in binary is
            actually representing a -1 int.

            A way to look at it, is to flip all bits, and then add 00..001.
            So for example, in a 4 bit representation, the value "1"
            can be represented with 0001. If we want to represent the value "-1",
            flipping it would give 1110, and adding 0001 will result in 1111,
            ie all 1s in binary.
            So, in a int, the value -1 in binary is represented with 32 bits all set
            to 1.
         */
        assertEquals(-1, HashFunctions.hashLongToInt(one32times));

        /*
            Note 8 hex is 1000 in binary
         */
        long singleOne = 0x80_00_00_00L;
        assertTrue(singleOne > 2_000_000_000);
        int hashed = HashFunctions.hashLongToInt(singleOne);
        /*
            a 1 followed by 31 0s in binary is actually the smallest possilbe int
         */
        assertTrue(hashed < -2_000_000_000);
        assertEquals(Integer.MIN_VALUE, hashed);
    }

    @Test
    public void testShiftVsMultiplicationDivision(){

        for(int i=0; i<1000; i++){

            int multiply = i * 2;
            int leftShift = i << 1;
            int divide = i / 2;
            int rightShift = i >>> 1;

            assertEquals(multiply, leftShift);
            assertEquals(divide, rightShift);

            /*
                Shifting bits is equivalent to multiply/divide by powers of 2.
                That is the reason why a lot of data sizes and operations rely
                on powers of 2, as multiplications/divisions become more efficient
             */
            int times8 = i * 8;
            int shift3 = i << 3;

            assertEquals(times8, shift3);
        }

    }

    @Test
    public void testHashLongToIntRevised(){

        long x = 0b0001_0000_0000_0000_0000_0000_0000_0000_____0000_0000_0000_0000_0000_0000_0000_0000L;
        long w = 0b0001_0000_0000_0000_0000_0000_0000_0000_____0000_0000_0000_0000_0000_0000_0001_0000L;
        long z = 0b0001_0001_0000_0000_0000_0000_0000_0000_____0000_0000_0000_0000_0000_0000_0000_0000L;

        assertNotEquals(HashFunctions.hashLongToInt(x),
                HashFunctions.hashLongToInt(w));
        //collision, as bit difference on left side
        assertEquals(HashFunctions.hashLongToInt(x),
                HashFunctions.hashLongToInt(z));

        //revised
        assertNotEquals(HashFunctions.hashLongToIntRevised(x),
                HashFunctions.hashLongToIntRevised(w));
        //no collision
        assertNotEquals(HashFunctions.hashLongToIntRevised(x),
                HashFunctions.hashLongToIntRevised(z));
    }

    @Test
    public void testHashStrings(){

        String ab = "ab";
        String ba = "ba";

        String exc = "!!"; //'!' has code 33
        String B = "B"; // has code 66

        assertEquals(HashFunctions.hashStringSum(ab),
                HashFunctions.hashStringSum(ba));

        assertEquals(HashFunctions.hashStringSum(exc),
                HashFunctions.hashStringSum(B));

        //no collision for these cases in Revised

        assertNotEquals(HashFunctions.hashStringSumRevised(ab),
                HashFunctions.hashStringSumRevised(ba));

        assertNotEquals(HashFunctions.hashStringSumRevised(exc),
                HashFunctions.hashStringSumRevised(B));
    }
}