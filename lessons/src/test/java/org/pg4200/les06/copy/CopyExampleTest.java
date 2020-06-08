package org.pg4200.les06.copy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CopyExampleTest {

    private static void checkSameData(String[] a, String[] b){

        if(a == null){
            assertNull(b);
            return;
        }

        assertEquals(a.length, b.length);
        for(int i=0; i<a.length; i++){
            assertEquals(a[i], b[i]);
        }
    }


    @Test
    public void testCopyArrayWrong(){

        String[] foo = {"hello", "there", "!"};
        String[] copy = CopyExample.copyWrong(foo);

        //same data
        checkSameData(foo, copy);

        //but side-effects on foo when changing copy
        String WRONG = "WRONG";
        copy[0] = WRONG;
        assertEquals(WRONG, foo[0]);
    }

    @Test
    public void testCopyArray(){

        String[] foo = {"hello", "there", "!"};
        String[] copy = CopyExample.copy(foo);

        //same data
        checkSameData(foo, copy);

        //must be no side-effects on foo when changing copy
        String ORIGINAL = foo[0];
        String WRONG = "WRONG";
        copy[0] = WRONG;
        assertNotEquals(WRONG, foo[0]);
        assertEquals(ORIGINAL, foo[0]);
    }


    private static void checkSameData(String[][] a, String[][] b){

        if(a == null){
            assertNull(b);
            return;
        }

        assertEquals(a.length, b.length);
        for(int i=0; i<a.length; i++){
            checkSameData(a[i], b[i]);
        }
    }


    @Test
    public void testCopyMatrixWrong(){

        String[][] foo = new String[2][];
        foo[0] = new String[]{"Hi", "first row"};
        foo[1] = new String[]{"Hello", "second row", "rows do not have to have same length"};
        String[][] copy = CopyExample.copyWrong(foo);

        checkSameData(foo, copy);

        //replacing a whole row will be fine here, ie no side-effect
        String WRONG = "WRONG";
        copy[0] = new String[]{WRONG};
        assertEquals(2, foo[0].length);
        assertNotEquals(WRONG, foo[0][0]);
        assertEquals("Hi", foo[0][0]);

        //however, changing one single value will have side-effects, as rows are not deep-copied
        copy[1][0] = WRONG;
        assertEquals(WRONG, foo[1][0]);
    }

    @Test
    public void testCopyMatrix(){

        String[][] foo = new String[2][];
        foo[0] = new String[]{"Hi", "first row"};
        foo[1] = new String[]{"Hello", "second row", "raws do not have to have same length"};
        String[][] copy = CopyExample.copy(foo);

        checkSameData(foo, copy);

        //replacing a whole row will be fine here, ie no side-effect
        String WRONG = "WRONG";
        copy[0] = new String[]{WRONG};
        assertEquals(2, foo[0].length);
        assertNotEquals(WRONG, foo[0][0]);
        assertEquals("Hi", foo[0][0]);

        //a proper deep-copy will avoid side effects
        copy[1][0] = WRONG;
        assertNotEquals(WRONG, foo[1][0]);
        assertEquals("Hello", foo[1][0]);
    }


}