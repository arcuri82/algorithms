package org.pg4200.les10.knapsack;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class BruteForceForKnapsack {


    /**
     * Enumerate all possible combinations of 0/1, and
     * return best among them.
     */
    public static boolean[] solve(KnapsackProblem problem){

        boolean[] buffer = new boolean[problem.getSize()];

        //start from index 0, return the best in the whole enumeration
        return recursive(problem, 0, buffer);
    }

    private static boolean[] recursive(KnapsackProblem problem, int index, boolean[] buffer){

        if(index >= buffer.length){
            //the index has reached the length of the array, so we are on a leaf
            return buffer.clone();
        }

        //on the left-subtree recursion, consider "true"
        buffer[index] = true;
        /*
            this is a cloned array, which is the best among all the arrays
            that share exactly the same prefix of 0/1 up to position index,
            with value at index being 1 (ie "true").

            Note: at each recursive call we increase the index by 1
         */
        boolean[] withTrue = recursive(problem, index+1, buffer);

        //on the right-subtree recursion, consider "false"
        buffer[index] = false;
        /*
            this is a cloned array, which is the best among all the arrays
            that share exactly the same prefix of 0/1 up to position index,
            with value at index being 0 (ie "false")
         */
        boolean[] withFalse = recursive(problem, index+1, buffer);

        /*
            so, we have 2 leaves: the best of the left-subtree, and
            the best on the right-subtree.
            based on their fitness, we return to the caller the best of
            these 2.
         */
        if(problem.evaluate(withTrue) > problem.evaluate(withFalse)){
            return withTrue;
        } else {
            return withFalse;
        }
    }
}
