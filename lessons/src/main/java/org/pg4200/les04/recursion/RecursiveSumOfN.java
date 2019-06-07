package org.pg4200.les04.recursion;

/**
 * Created by arcuri82 on 06-Jun-19.
 */
public class RecursiveSumOfN {


    /*
        Return the sum of all values from 1 to n, ie
        1 + 2 + ... + (n-2) + (n-1) + n
     */
    public static int sumOfAllValues(int n){

        if(n < 0){
            throw new IllegalArgumentException("Invalid negative value: " + n);
        }

        /*
            Always remember the stopping condition!
         */
        if(n==0 || n==1){
            return n;
        }

        /*
            Recursion calls are often done on "smaller" inputs.
            The keep calling and decreasing until a stopping condition is met.
         */
        return n + sumOfAllValues(n-1);
    }


    public static int sumOfAllValuesNoStopping(int n){

        /*
            This keep on calling itself, with no stopping criterion
         */
        return n + sumOfAllValuesNoStopping(n-1);
    }



}
