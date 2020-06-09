package org.pg4200.sol10;

import org.pg4200.les10.knapsack.KnapsackProblem;

import java.util.Objects;
import java.util.Random;

public class RandomForKnapsackOptimized {


    public static boolean[] solve(int maxIterations, KnapsackProblem problem){

        if(maxIterations < 1){
            throw new IllegalArgumentException("Invalid number of iterations");
        }
        Objects.requireNonNull(problem);

        Random random = new Random();

        final int n = problem.getSize();

        boolean[] solution = new boolean[n];
        boolean[] buffer = new boolean[n];

        sample(solution, random);

        for(int i=1; i<maxIterations; i++){
            sample(buffer, random);

            if(problem.evaluate(buffer) > problem.evaluate(solution)){
                boolean[] tmp = solution;
                solution = buffer;
                buffer = tmp;
            }
        }

        return solution;
    }

    private static void sample(boolean[] buffer, Random random){
        for(int i=0; i<buffer.length; i++){
            buffer[i] = random.nextBoolean();
        }
    }
}
