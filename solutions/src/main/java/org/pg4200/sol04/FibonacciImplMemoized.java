package org.pg4200.sol04;

import org.pg4200.ex04.Fibonacci;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class FibonacciImplMemoized implements Fibonacci {

    /*
        Note: here using Integer instead of int, because I need to know
        when a value has not been computed yet by checking if null.
        If I rather was using int, then I would not be able to distinguish a 0 from
        a non-set value.
        However, in the case of Fibonacci sequence, it would not be be a problem, because
        only the very first value can be a 0.
     */
    private final Integer[] cache = new Integer[100];

    @Override
    public int compute(int n) {

        if(n < 0){
            throw new IllegalArgumentException("Negative input: " + n);
        }

        if(n==0 || n==1){
            return n;
        }

        if(n < cache.length && cache[n] != null){
            return cache[n];
        }

        int f = compute(n-2) + compute(n-1);

        if(n < cache.length){
            cache[n] = f;
        }

        return f;
    }
}
