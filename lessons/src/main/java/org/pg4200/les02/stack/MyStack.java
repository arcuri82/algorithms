package org.pg4200.les02.stack;

/**
 *  Stacks ar LIFO: Last In, First Out.
 */
public interface MyStack<T> {

    /**
     * Add the value on top of the stack
     */
    void push(T value);

    /**
     * Retrieve the value on top of the stack,
     * but do not alter the stack itself.
     *
     * Throw an exception if the stack is empty
     */
    T peek();

    /**
     * Remove and return the value on top of the stack.
     *
     * Throw an exception if the stack is empty.
     */
    T pop();


    int size();


    default boolean isEmpty(){
        return size() == 0;
    }
}
