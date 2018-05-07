package org.pg4200.les10.queens;

import java.util.Objects;

/**
 * Created by arcuri82 on 09-Oct-17.
 */
public class QueensProblem {

    public static boolean isCorrect(int[] positions){
        return evaluate(positions) == 0;
    }

      /*
           ----
         3|  q |
         2|q   |
         1|   q|
         0| q  |
           ----
           0123

         positions = {2,0,3,1}
     */

    /**
     *
     * @param positions, row indices on the matrix for the given column indices,
     *                   ie, positions[0] is the row of the queen in column 0
     * @return a positive heuristics telling how many queens are in conflict.
     *          If there is no conflict, and solution is found, return 0.
     * @throws NullPointerException if the input array is null
     * @throws IllegalArgumentException if the input array is not valid
     */
    public static int evaluate(int[] positions){

        validatePositions(positions);

        int penalty = 0;

        for(int i=0; i<positions.length-1; i++){

            for(int j=i+1; j<positions.length; j++){

                /*
                    Need to check if the hit on the diagonals.
                 */

                int a = positions[i];
                int b = positions[j];
                int rowDiff = a - b;
                int columnDiff = j - i;

                assert columnDiff > 0;

                if(a > b){
                    assert rowDiff > 0;

                    /*
                       |  i
                       |   \
                       |    j
                       |

                       this means the Queen in column i is on higher
                       row than j.
                     */


                    if(rowDiff == columnDiff){
                        penalty++;
                    }

                } else {
                    assert a < b && rowDiff < 0;

                     /*
                       |    j
                       |   /
                       |  i
                       |

                       this means the Queen in column i is on lower
                       row than j.
                     */


                    if(-rowDiff == columnDiff){
                        penalty++;
                    }
                }
            }
        }

        return penalty;
    }

    private static void validatePositions(int[] positions){
        Objects.requireNonNull(positions);

        boolean[] presences = new boolean[positions.length];

        for(int i=0; i<positions.length; i++){
            int pos = positions[i];
            if(pos < 0 || pos >= presences.length){
                throw new IllegalArgumentException("Invalid position value " + pos);
            }
            presences[pos] =  true;
        }

        for(int i=0; i<presences.length; i++){
            if(! presences[i]){
                throw new IllegalArgumentException("Missing index for " + i);
            }
        }
    }
}
