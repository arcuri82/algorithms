package org.pg4200.optimization.knapsack;

import java.util.Objects;
import java.util.Random;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class RandomForKnapsack {


    public boolean[] solve(int maxIterations, KnapsackProblem problem){

        if(maxIterations < 1){
            throw new IllegalArgumentException("Invalid number of iterations");
        }
        Objects.requireNonNull(problem);

        Random random = new Random();

        boolean[] solution = null;

        /*
            Just sample at random, and keep track of best
            solution found so far
         */

        for(int i=0; i<maxIterations; i++){
            boolean[] sampled = sample(problem.getSize(), random);

            if(solution==null ||
                    problem.evaluate(sampled) > problem.evaluate(solution)){
                solution = sampled;
            }
        }

        return solution;
    }

    private boolean[] sample(int n, Random random){

        boolean[] solution = new boolean[n];
        for(int i=0; i<solution.length; i++){
            solution[i] = random.nextBoolean();
        }

        return solution;
    }
}
