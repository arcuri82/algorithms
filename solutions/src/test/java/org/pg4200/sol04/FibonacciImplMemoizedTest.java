package org.pg4200.sol04;

import org.pg4200.ex04.Fibonacci;
import org.pg4200.ex04.FibonacciTestTemplate;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
class FibonacciImplMemoizedTest extends FibonacciTestTemplate {

    @Override
    protected Fibonacci getInstance() {
        return new FibonacciImplMemoized();
    }
}