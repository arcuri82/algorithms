package org.pg4200.sol10;

import org.pg4200.les10.queens.QueensProblem;

import java.util.Random;

/**
 * Created by arcuri82 on 16-Oct-17.
 */
public class SteepestAscentHCforQueens {

    public static int[] solve(int n){

        if(n < 4){
            throw new IllegalArgumentException("Too small board");
        }

        int[] solution = new int[n];
        for(int i=0; i<n; i++){
            solution[i] = i;
        }

        while(true){
            //(re)-start from a random point
            shuffleArray(solution);

            int fitness = QueensProblem.evaluate(solution);

            if(fitness == 0){
                return solution;
            }

            while(true) {

                int[] bestNeighbour = null;
                int bestNeighbourFitness = Integer.MAX_VALUE;

                for (int i = 0; i < n - 1; i++) {
                    for (int j = i + 1; j < n; j++) {
                        swap(solution, i, j);

                        int neighbourFitness = QueensProblem.evaluate(solution);

                        if(neighbourFitness == 0){
                            return solution;
                        }

                        if (neighbourFitness < fitness) {
                            if(bestNeighbour == null || neighbourFitness < bestNeighbourFitness){
                                bestNeighbour = solution.clone();
                                bestNeighbourFitness = neighbourFitness;
                            }
                        }

                        //swap back
                        swap(solution, i, j);
                    }
                }

                if(bestNeighbourFitness < fitness){
                    solution = bestNeighbour;
                    fitness = bestNeighbourFitness;
                } else {
                    //local optimum
                    break;
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /**
     * Ridiculously enough, Java API has no built-in method
     * to shuffle arrays...
     *
     * @param array
     */
    private static void shuffleArray(int[] array) {
        int index, temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
}
