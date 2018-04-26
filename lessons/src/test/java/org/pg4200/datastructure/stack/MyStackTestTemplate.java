package org.pg4200.datastructure.stack;

import org.junit.Before;
import org.junit.Test;
import org.pg4200.les02.stack.MyStack;

import static org.junit.Assert.*;

public abstract class MyStackTestTemplate {

    protected abstract <T> MyStack<T> getInstance(Class<T> klass);

    private MyStack<Integer> stack;

    @Before
    public void init(){
        stack = getInstance(Integer.class);
    }

    @Test
    public void testEmpty(){

        assertTrue(stack.isEmpty());
    }

    @Test
    public void testFailToPopOnEmpty(){

        try{
            stack.pop();
            fail();
        } catch (RuntimeException e){
            //expected
        }
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