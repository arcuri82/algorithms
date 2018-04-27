package org.pg4200.les02.stack;

public class MyStackArrayTest extends MyStackTestTemplate{


    @Override
    protected <T> MyStack<T> getInstance(Class<T> klass) {
        return new MyStackArray<>();
    }

}