package org.pg4200.les10.knapsack;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class BruteForceForKnapsackTest {

    private void test(KnapsackInstanceWithSolution p, double optimum){

        boolean[] res = BruteForceForKnapsack.solve(p.getProblem());

        double fitness = p.getProblem().evaluate(res);

        assertEquals(optimum, fitness, 0.001);

        double best = p.getBestFitness();

        assertEquals(best, fitness, 0.001);
    }

    @Test
    public void testP05(){

        test(KnapsackInstanceWithSolution.problemP05(), 51d);
    }

    @Test
    public void testP15(){

        test(KnapsackInstanceWithSolution.problemP15(), 1458d);
    }

    @Test
    public void testP24(){

        test(KnapsackInstanceWithSolution.problemP24(), 13549094d);
    }

    @Disabled
    @Test
    public void testP100(){

        test(KnapsackInstanceWithSolution.problemP100(), 67165d);

        /*
            On a 2016 machine, P24 takes 1-2 seconds.
            How long will P100 take?
            Let's make some high level calculations...

            LOWER BOUND of 1 seconds per 2^24

            2^100 / 2^24 seconds -> 2^76 seconds

            2^76 / (60 * 60 * 24 * 365)  years, ie

            2,300,000,000,000,000  years

            ie you need to wait AT LEAST 2 Quadrillion years
          */
    }

}