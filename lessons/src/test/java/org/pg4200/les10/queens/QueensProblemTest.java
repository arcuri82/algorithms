package org.pg4200.les10.queens;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class QueensProblemTest {


    @Test
    public void test4x4_line(){

        /*
           ----
         3|   q|
         2|  q |
         1| q  |
         0|q   |
           ----
           0123
        */
        int[] line = {0,1,2,3};

        int fitness = QueensProblem.evaluate(line);

        /*
            3 + 2 + 1 + 0
         */
        assertEquals(6, fitness);
        assertFalse(QueensProblem.isCorrect(line));
    }

    @Test
    public void test4x4_solution(){

        int[] positions = {2,0,3,1};

        int fitness = QueensProblem.evaluate(positions);

       /*
           ----
         3|  q |
         2|q   |
         1|   q|
         0| q  |
           ----
           0123
        */
        assertEquals(0, fitness);
        assertTrue(QueensProblem.isCorrect(positions));
    }


    @Test
    public void test8x8_solution(){

        int[] positions = {4,2,0,6,1,7,5,3};

        int fitness = QueensProblem.evaluate(positions);


        assertEquals(0, fitness);
        assertTrue(QueensProblem.isCorrect(positions));
    }

    @Test
    public void test8x8_wrong(){

        int[] positions = {4,2,0,6,1,7,3,5};

        int fitness = QueensProblem.evaluate(positions);

        assertTrue(fitness > 0);
        assertFalse(QueensProblem.isCorrect(positions));
    }



    private void testRandom(int n){

        int[] positions = RandomForQueens.solve(n);

        int fitness = QueensProblem.evaluate(positions);

        assertEquals(0, fitness);
        assertTrue(QueensProblem.isCorrect(positions));

        System.out.println(Arrays.toString(positions));
    }



    @Test
    public void testRandom_8x8(){

        testRandom(8);
    }

    @Test
    public void testRandom_16x16(){

        testRandom(16);
    }

    @Test
    public void testRandom_20x20(){

        /*
            This is much slower than the 16 case,
            but still on average shouldn't take
            more than a few minutes
         */

        testRandom(20);
    }


    private void testHillClimbing(int n){

        int[] positions = HillClimbingForQueens.solve(n);

        int fitness = QueensProblem.evaluate(positions);

        assertEquals(0, fitness);
        assertTrue(QueensProblem.isCorrect(positions));

        System.out.println(Arrays.toString(positions));
    }

    @Test
    public void testHillClimbing_8x8(){

        testHillClimbing(8);
    }

    @Test
    public void testHillClimbing_16x16(){

        testHillClimbing(16);
    }

    @Test
    public void testHillClimbing_20x20(){

        testHillClimbing(20);
    }

    @Test
    public void testHillClimbing_100x100(){

        /*
            Even on a 100x100 board, HC is still fast
         */
        testHillClimbing(100);
    }
}