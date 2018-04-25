package org.pg4200.datastructure.stack;

import org.pg4200.datastructure.delete.ArrayDeleteContainer;
import org.pg4200.datastructure.delete.DeleteContainer;
import org.pg4200.datastructure.delete.ListDeleteContainer;

public class ContainerDelegateStack<T> implements MyStack<T> {

    private DeleteContainer<T> delegate;

    /**
     * Use private constructor to prevent clients from
     * directly instantiate this class
     */
    private ContainerDelegateStack(){
    }

    public static <T> ContainerDelegateStack<T> backedByArray(Class<T> klass){
        ContainerDelegateStack<T> container =  new ContainerDelegateStack<>();
        container.delegate = new ArrayDeleteContainer<>();
        return container;
    }

    public static <T> ContainerDelegateStack<T> backedByList(Class<T> klass){
        ContainerDelegateStack<T> container =  new ContainerDelegateStack<>();
        container.delegate = new ListDeleteContainer<>();
        return container;
    }

    @Override
    public void push(T value) {
        delegate.add(value);
    }

    @Override
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException();
        }
        return delegate.get(size()-1);
    }

    @Override
    public T pop() {
        if(isEmpty()){
            throw new RuntimeException();
        }
        T value = delegate.get(size()-1);
        delegate.delete(size()-1);
        return value;
    }

    @Override
    public int size() {
        return delegate.size();
    }
}
