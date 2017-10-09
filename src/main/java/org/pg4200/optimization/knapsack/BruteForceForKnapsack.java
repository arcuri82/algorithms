package org.pg4200.optimization.knapsack;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class BruteForceForKnapsack {



    public static boolean[] solve(KnapsackProblem problem){

        boolean[] buffer = new boolean[problem.getSize()];

        return recursive(problem, 0, buffer);
    }

    private static boolean[] recursive(KnapsackProblem problem, int index, boolean[] buffer){

        if(index >= buffer.length){
            return buffer.clone();
        }

        buffer[index] = true;
        boolean[] withTrue = recursive(problem, index+1, buffer);

        buffer[index] = false;
        boolean[] withFalse = recursive(problem, index+1, buffer);

        if(problem.evaluate(withTrue) > problem.evaluate(withFalse)){
            buffer[index] = true;
            return withTrue;
        } else {
            buffer[index] = false;
            return withFalse;
        }
    }
}
