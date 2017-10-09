package org.pg4200.optimization.knapsack;

import java.util.Objects;

/**
 * Instances taken from:
 *
 * https://people.sc.fsu.edu/~jburkardt/datasets/knapsack_01/knapsack_01.html
 *
 * Created by arcuri82 on 09-Oct-17.
 */
public class KnapsackInstanceWithSolution {

    private final KnapsackProblem problem;

    private final boolean[] best;

    private KnapsackInstanceWithSolution(KnapsackProblem problem, boolean[] best) {
        this.problem = Objects.requireNonNull(problem);
        this.best = Objects.requireNonNull(best);

        if(best.length != problem.getSize()){
            throw new IllegalArgumentException("Size mismatch");
        }
    }

    public KnapsackProblem getProblem() {
        return problem;
    }

    public boolean[] getBest() {
        return best.clone();
    }

    public double getBestFitness(){
        return problem.evaluate(best);
    }

    public static KnapsackInstanceWithSolution problemP01(){

        return new KnapsackInstanceWithSolution(
                new KnapsackProblem(
                        26,
                        new double[]{12, 7, 11, 8, 9},
                        new double[]{24, 13, 23, 15, 16}
                ),
                new boolean[]{false, true, true, true, false}
        );
    }
}
