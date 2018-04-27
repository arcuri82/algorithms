package org.pg4200.les02.stack;

public class MyStackContainerDelegateListTest extends MyStackTestTemplate{

    @Override
    protected <T> MyStack<T> getInstance(Class<T> klass) {
        return MyStackContainerDelegate.backedByList(klass);
    }
}