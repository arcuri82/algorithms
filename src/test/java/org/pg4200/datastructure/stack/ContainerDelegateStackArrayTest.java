package org.pg4200.datastructure.stack;

import static org.junit.Assert.*;

public class ContainerDelegateStackArrayTest extends MyStackTestTemplate{

    @Override
    protected <T> MyStack<T> getInstance(Class<T> klass) {
        return ContainerDelegateStack.backedByArray(klass);
    }
}