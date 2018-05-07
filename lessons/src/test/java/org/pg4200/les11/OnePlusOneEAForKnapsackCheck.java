package org.pg4200.les11;

import org.pg4200.les10.knapsack.GreedyForKnapsack;
import org.pg4200.les10.knapsack.KnapsackInstanceWithSolution;
import org.pg4200.les11.ea.OnePlusOneEAForKnapsack;

public class OnePlusOneEAForKnapsackCheck {


    private static void check(KnapsackInstanceWithSolution p, int iterations) {

        boolean[] greedy = GreedyForKnapsack.solveByBestRatioFirst(p.getProblem());

        boolean[] opoea = OnePlusOneEAForKnapsack.solve(iterations, p.getProblem());

        double fg = p.getProblem().evaluate(greedy);
        double og = p.getProblem().evaluate(opoea);

        System.out.print("N=" + p.getProblem().getSize());
        System.out.print(" , Greedy=" + fg);
        System.out.print(" , (1+1)EA=" + og);

        String label;
        if (og == p.getBestFitness()) {
            label = "Optimal";
        } else if (og > fg) {
            label = "Improved";
        } else {
            label = "Worse";
        }

        System.out.println("  ->  " + label);
    }

    public static void main(String[] args) {

        int iterations = 100_000;

        check(KnapsackInstanceWithSolution.problemP05(), iterations);
        check(KnapsackInstanceWithSolution.problemP15(), iterations);
        check(KnapsackInstanceWithSolution.problemP24(), iterations);
        check(KnapsackInstanceWithSolution.problemP100(), iterations);
    }
}