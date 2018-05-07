package org.pg4200.sol11;

import org.pg4200.les10.queens.QueensProblem;

import java.util.Random;

public class OnePlusOneEAForQueens {

    public static int[] solve(int n){

        if(n < 4){
            throw new IllegalArgumentException("Too small board");
        }

        int[] solution = new int[n];
        for(int i=0; i<n; i++){
            solution[i] = i;
        }

        shuffleArray(solution);

        Random random = new Random();
        final double p = 1d / (double) n;

        int fitness = QueensProblem.evaluate(solution);

        while(true){

            if(fitness == 0){
                return solution;
            }

            int[] offspring = solution.clone();

            boolean mutated = false;

            while(!mutated) {

                for (int j = 1; j < n; j++) {
                    if (random.nextDouble() < p) {

                        int i = random.nextInt(j);

                        swap(offspring, i , j);

                        mutated = true;
                    }
                }
            }

            int osf = QueensProblem.evaluate(offspring);

            if(osf <= fitness){
                solution = offspring;
                fitness = osf;
            }
        }
    }

    private static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }


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
