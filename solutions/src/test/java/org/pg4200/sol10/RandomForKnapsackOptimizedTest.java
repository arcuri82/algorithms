package org.pg4200.sol10;

import org.junit.jupiter.api.Test;
import org.pg4200.les10.knapsack.KnapsackInstanceWithSolution;
import org.pg4200.les10.knapsack.RandomForKnapsack;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RandomForKnapsackOptimizedTest {

    private void test(int iterations, KnapsackInstanceWithSolution p, double optimum){

        boolean[] res = RandomForKnapsackOptimized.solve(iterations, p.getProblem());

        double fitness = p.getProblem().evaluate(res);

        assertEquals(optimum, fitness, 0.001);

        double best = p.getBestFitness();

        assertEquals(best, fitness, 0.001);
    }

    /*
        Note: here we put high enough number of iterations (compared to the size
        of search space) that it is more likely to be hit on the head by a meteor
        while reading this text than these tests will fail.
     */

    @Test
    public void testP05(){

        test(1000, KnapsackInstanceWithSolution.problemP05(), 51d);
    }

    @Test
    public void testP15(){

        test(1_000_000, KnapsackInstanceWithSolution.problemP15(), 1458d);
    }

}