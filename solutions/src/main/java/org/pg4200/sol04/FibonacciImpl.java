package org.pg4200.sol04;

import org.pg4200.ex04.Fibonacci;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class FibonacciImpl implements Fibonacci {


    @Override
    public int compute(int n) {
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
        return compute(n-1) + compute(n-2);
    }
}
