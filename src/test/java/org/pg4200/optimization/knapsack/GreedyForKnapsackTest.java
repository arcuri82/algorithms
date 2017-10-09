package org.pg4200.optimization.knapsack;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class GreedyForKnapsackTest {


    @Test
    public void testSolveByHeavierFirst(){

        KnapsackInstanceWithSolution p = KnapsackInstanceWithSolution.problemP01();

        boolean[] res = GreedyForKnapsack.solveByHeavierFirst(p.getProblem());

        double fitness = p.getProblem().evaluate(res);

        assertEquals(47d, fitness, 0.001);

        double best = p.getBestFitness();

        assertNotEquals(best, fitness);
        assertTrue(best > fitness);
    }

    @Test
    public void testSolveByLigheterFirst(){

        KnapsackInstanceWithSolution p = KnapsackInstanceWithSolution.problemP01();

        boolean[] res = GreedyForKnapsack.solveByLighterFirst(p.getProblem());

        double fitness = p.getProblem().evaluate(res);

        assertEquals(44d, fitness, 0.001);

        double best = p.getBestFitness();

        assertNotEquals(best, fitness);
        assertTrue(best > fitness);
    }

    @Test
    public void testSolveByBestRatioFirst(){

        KnapsackInstanceWithSolution p = KnapsackInstanceWithSolution.problemP01();

        boolean[] heavier = GreedyForKnapsack.solveByHeavierFirst(p.getProblem());
        boolean[] ratio = GreedyForKnapsack.solveByBestRatioFirst(p.getProblem());

        assertArrayEquals(heavier, ratio);
    }
}