package org.pg4200.sol10;

import org.junit.jupiter.api.Test;
import org.pg4200.les10.queens.QueensProblem;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by arcuri82 on 16-Oct-17.
 */
public class SteepestAscentHCforQueensTest {

    private void testSteepestAscent(int n){

        int[] positions = SteepestAscentHCforQueens.solve(n);

        int fitness = QueensProblem.evaluate(positions);

        assertEquals(0, fitness);
        assertTrue(QueensProblem.isCorrect(positions));

        System.out.println(Arrays.toString(positions));
    }

    @Test
    public void testSteepestAscent_8x8(){

        testSteepestAscent(8);
    }

    @Test
    public void testSteepestAscent_16x16(){

        testSteepestAscent(16);
    }

    @Test
    public void testSteepestAscent_20x20(){

        testSteepestAscent(20);
    }

    @Test
    public void testSteepestAscent_100x100(){

        /*
            Even on a 100x100 board, HC is still fast
         */
        testSteepestAscent(100);
    }
}