package org.pg4200.datastructure.stack;

import org.pg4200.les02.stack.ContainerDelegateStack;
import org.pg4200.les02.stack.MyStack;

public class ContainerDelegateStackListTest extends MyStackTestTemplate{

    @Override
    protected <T> MyStack<T> getInstance(Class<T> klass) {
        return ContainerDelegateStack.backedByList(klass);
    }
}