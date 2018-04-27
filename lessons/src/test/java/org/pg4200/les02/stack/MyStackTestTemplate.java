package org.pg4200.les02.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public abstract class MyStackTestTemplate {

    protected abstract <T> MyStack<T> getInstance(Class<T> klass);

    private MyStack<Integer> stack;

    @BeforeEach
    public void init(){
        stack = getInstance(Integer.class);
    }

    @Test
    public void testEmpty(){

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testFailToPopOnEmpty(){

        assertThrows(RuntimeException.class,
                () -> stack.pop());
    }

    @Test
    public void testPush(){

        assertEquals(0, stack.size());

        stack.push(5);

        assertEquals(1, stack.size());
    }


    @Test
    public void testPushAndPeek(){

        int n = 42;

        stack.push(n);

        int res = stack.peek();

        assertEquals(n, res);
    }


    @Test
    public void testPushAndPop(){

        int n = 42;

        assertEquals(0, stack.size());

        stack.push(n);

        assertEquals(1, stack.size());

        int res = stack.pop();

        assertEquals(n, res);
        assertEquals(0, stack.size());
    }


    @Test
    public void testPushAndPopThreeTimes(){

        stack.push(0);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop().intValue());
        assertEquals(1, stack.pop().intValue());
        assertEquals(0, stack.pop().intValue());
    }
}