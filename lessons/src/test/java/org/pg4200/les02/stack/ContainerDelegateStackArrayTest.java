package org.pg4200.les02.stack;

public class ContainerDelegateStackArrayTest extends MyStackTestTemplate{

    @Override
    protected <T> MyStack<T> getInstance(Class<T> klass) {
        return ContainerDelegateStack.backedByArray(klass);
    }
}