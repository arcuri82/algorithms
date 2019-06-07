package org.pg4200.ex04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public abstract class FibonacciTestTemplate {


    protected abstract Fibonacci getInstance();


    @Test
    public void testInvalid(){
        assertThrows(IllegalArgumentException.class,() ->
                getInstance().compute(-1));
    }

    @Test
    public void testCompute00(){
        assertEquals(0, getInstance().compute(0));
    }

    @Test
    public void testCompute01(){
        assertEquals(1, getInstance().compute(1));
    }

    @Test
    public void testCompute02(){
        assertEquals(1, getInstance().compute(2));
    }

    @Test
    public void testCompute03(){
        assertEquals(2, getInstance().compute(3));
    }

    @Test
    public void testCompute04(){
        assertEquals(3, getInstance().compute(4));
    }

    @Test
    public void testCompute05(){
        assertEquals(5, getInstance().compute(5));
    }

    @Test
    public void testCompute06(){
        assertEquals(8, getInstance().compute(6));
    }

    @Test
    public void testCompute07(){
        assertEquals(13, getInstance().compute(7));
    }

    @Test
    public void testCompute08(){
        assertEquals(21, getInstance().compute(8));
    }

    @Test
    public void testCompute09(){
        assertEquals(34, getInstance().compute(9));
    }


    @Test
    public void testComputeHighValueNoException(){
        //just check it is not throwing any exception
        getInstance().compute(100);
    }
}