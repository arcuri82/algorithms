package org.pg4200.les10.knapsack;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class GreedyForKnapsackTest {

    @Test
    public void testSolveByHeavierFirst(){

        KnapsackInstanceWithSolution p = KnapsackInstanceWithSolution.problemP05();

        boolean[] res = GreedyForKnapsack.solveByHeavierFirst(p.getProblem());

        double fitness = p.getProblem().evaluate(res);

        assertEquals(47d, fitness, 0.001);

        double best = p.getBestFitness();

        assertNotEquals(best, fitness);
        assertTrue(best > fitness);
    }

    @Test
    public void testSolveByLighterFirst(){

        KnapsackInstanceWithSolution p = KnapsackInstanceWithSolution.problemP05();

        boolean[] res = GreedyForKnapsack.solveByLighterFirst(p.getProblem());

        double fitness = p.getProblem().evaluate(res);

        assertEquals(44d, fitness, 0.001);

        double best = p.getBestFitness();

        assertNotEquals(best, fitness);
        assertTrue(best > fitness);
    }

    @Test
    public void testSolveByBestRatioFirst(){

        KnapsackInstanceWithSolution p = KnapsackInstanceWithSolution.problemP05();

        boolean[] heavier = GreedyForKnapsack.solveByHeavierFirst(p.getProblem());
        boolean[] ratio = GreedyForKnapsack.solveByBestRatioFirst(p.getProblem());

        assertArrayEquals(heavier, ratio);
    }


    @Test
    public void testLargeInstance(){

        KnapsackInstanceWithSolution p = KnapsackInstanceWithSolution.problemP100();

        boolean[] lighter = GreedyForKnapsack.solveByLighterFirst(p.getProblem());
        boolean[] heavier = GreedyForKnapsack.solveByHeavierFirst(p.getProblem());
        boolean[] ratio = GreedyForKnapsack.solveByBestRatioFirst(p.getProblem());
        boolean[] best = p.getBest();


        double fit_lighter = p.getProblem().evaluate(lighter);
        double fit_heavier = p.getProblem().evaluate(heavier);
        double fit_ratio = p.getProblem().evaluate(ratio);
        double fit_best = p.getProblem().evaluate(best);

        assertEquals(67165d, fit_best, 0.001);

        assertTrue(fit_lighter < fit_best);
        assertTrue(fit_heavier < fit_best);
        assertTrue(fit_ratio < fit_best);

        assertTrue(fit_ratio > fit_lighter);
        assertTrue(fit_ratio > fit_heavier);

        System.out.println("Heavier: fitness=" + fit_heavier +", items=" + p.getProblem().numberOfSelectedItems(heavier));
        System.out.println("Lighter: fitness=" + fit_lighter+", items=" + p.getProblem().numberOfSelectedItems(lighter));
        System.out.println("Ratio: fitness=" + fit_ratio+", items=" + p.getProblem().numberOfSelectedItems(ratio));
        System.out.println("Best: fitness=" + fit_best+", items=" + p.getProblem().numberOfSelectedItems(best));

        double missingPercentage = (fit_best - fit_ratio) / fit_best;
        assertTrue(missingPercentage < 0.01);

        assertEquals(34, p.getProblem().numberOfDifferences(ratio, best));
    }
}