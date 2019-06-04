package org.pg4200.les02.stack;

import org.pg4200.les02.list.MyArrayList;
import org.pg4200.les02.list.MyList;
import org.pg4200.les02.list.MyLinkedList;

public class MyStackDelegate<T> implements MyStack<T> {

    private MyList<T> delegate;

    /**
     * Use private constructor to prevent clients from
     * directly instantiate this class.
     *
     * New instances will be returned ONLY via static methods.
     */
    private MyStackDelegate(){
    }

    /**
     *  Return a new instance of this class, where the internal delegate list
     *  is of type MyArrayList
     */
    public static <T> MyStackDelegate<T> backedByArrayList(Class<T> klass){
        MyStackDelegate<T> container =  new MyStackDelegate<>();
        container.delegate = new MyArrayList<>();
        return container;
    }

    /**
     *  Return a new instance of this class, where the internal delegate list
     *  is of type MyLinkedList
     */
    public static <T> MyStackDelegate<T> backedByLinkedList(Class<T> klass){
        MyStackDelegate<T> container =  new MyStackDelegate<>();
        container.delegate = new MyLinkedList<>();
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
