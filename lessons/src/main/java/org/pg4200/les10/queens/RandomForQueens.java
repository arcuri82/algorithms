package org.pg4200.les10.queens;

import java.util.Random;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class RandomForQueens {

    public static int[] solve(int n){

        if(n < 4){
            throw new IllegalArgumentException("Too small board");
        }

        int[] solution = new int[n];
        for(int i=0; i<n; i++){
            solution[i] = i;
        }

        while(true){
            shuffleArray(solution);
            if(QueensProblem.isCorrect(solution)){
                return solution;
            }
        }
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
