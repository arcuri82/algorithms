package org.pg4200.datastructure.stack;

import org.pg4200.les02.stack.ArrayStack;
import org.pg4200.les02.stack.MyStack;

public class ArrayStackTest extends MyStackTestTemplate{


    @Override
    protected <T> MyStack<T> getInstance(Class<T> klass) {
        return new ArrayStack<>();
    }

}