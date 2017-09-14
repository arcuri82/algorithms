package org.pg4200.datastructure.map.hash;

import org.junit.Test;

import static org.junit.Assert.*;

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