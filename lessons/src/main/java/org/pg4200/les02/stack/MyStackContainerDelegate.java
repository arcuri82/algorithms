package org.pg4200.les02.stack;

import org.pg4200.les02.generic.MyGenericContainerArray;
import org.pg4200.les02.generic.MyGenericContainer;
import org.pg4200.les02.generic.MyGenericContainerList;

public class MyStackContainerDelegate<T> implements MyStack<T> {

    private MyGenericContainer<T> delegate;

    /**
     * Use private constructor to prevent clients from
     * directly instantiate this class
     */
    private MyStackContainerDelegate(){
    }

    public static <T> MyStackContainerDelegate<T> backedByArray(Class<T> klass){
        MyStackContainerDelegate<T> container =  new MyStackContainerDelegate<>();
        container.delegate = new MyGenericContainerArray<>();
        return container;
    }

    public static <T> MyStackContainerDelegate<T> backedByList(Class<T> klass){
        MyStackContainerDelegate<T> container =  new MyStackContainerDelegate<>();
        container.delegate = new MyGenericContainerList<>();
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
