package org.pg4200.les04.recursion;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class Fibonacci {

    public static int fibonacci(int n){

        if(n < 0){
            throw new IllegalArgumentException("Invalid negative value: " + n);
        }

        /*
            Always remember the stopping condition!
         */
        if(n==0 || n==1){
            return n;
        }

        //there can be more than a recursive call
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
