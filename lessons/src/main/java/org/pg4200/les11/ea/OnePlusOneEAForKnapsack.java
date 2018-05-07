package org.pg4200.les11.ea;

import org.pg4200.les10.knapsack.KnapsackProblem;

import java.util.Objects;
import java.util.Random;

public class OnePlusOneEAForKnapsack {

    public static boolean[] solve(int maxIterations, KnapsackProblem problem){

        if(maxIterations < 1){
            throw new IllegalArgumentException("Invalid number of iterations");
        }
        Objects.requireNonNull(problem);

        Random random = new Random();

        final int n = problem.getSize();
        final double p = 1d / (double) n;

        boolean[] solution = sample(n, random);


        for(int i=1; i<maxIterations; i++){
            boolean[] offspring = solution.clone();

            boolean mutated = false;

            while(!mutated) {
                /*
                    (1 - 1/n)^n ~ 0.36
                    probability of nothing mutated
                 */

                for (int j = 0; j < n; j++) {
                    if (random.nextDouble() < p) {
                        offspring[j] = !offspring[j];
                        mutated = true;
                    }
                }
            }

            /*
                Note: it is CRITICAL here that we have ">=" instead of
                ">". This is to allow "random walks" on "fitness plateaus",
                ie still explore the search space as long as individual does
                not get worse.
              */

            if(problem.evaluate(offspring) >= problem.evaluate(solution)){
                solution = offspring;
            }
        }

        return solution;
    }

    private static boolean[] sample(int n, Random random){

        boolean[] solution = new boolean[n];
        for(int i=0; i<solution.length; i++){
            solution[i] = random.nextBoolean();
        }

        return solution;
    }

}
