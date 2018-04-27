package org.pg4200.les02.stack;

public class MyStackLinkedListTest extends MyStackTestTemplate{

    @Override
    protected <T> MyStack<T> getInstance(Class<T> klass) {
        return new MyStackLinkedList<>();
    }
}