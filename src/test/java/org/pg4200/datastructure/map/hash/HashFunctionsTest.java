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
}