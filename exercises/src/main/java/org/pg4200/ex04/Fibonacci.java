package org.pg4200.ex04;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public interface Fibonacci {

    /**
     * Compute the Fibonacci number f(n) = f(n-2) + f(n-1).
     *
     * @throws IllegalArgumentException if n is negative
     */
    int compute(int n) throws IllegalArgumentException;
}
