package org.pg4200.les02.list;

/**
 *  Before we defined a list interface to hold String objects.
 *  But what if I need to store more complex objects, like an hypothetical
 *  Song, PurchaseOrder, etc?
 *
 *  As the "type" of what we store is not important, we use Java "Generics"
 *
 *  Furthermore, now we also consider operations like delete and insertions in
 *  any position.
 *
 * Created by arcuri82 on 15-Aug-17.
 */
public interface MyList<T> {

    /**
     *  Delete the element at position "index".
     *
     *  Throw an exception if index is invalid
     */
    void delete(int index);

    /*
        Interface can have code, but no state.
        Here, we use the "size()" method.
        How to use "size()" to implement "isEmpty()"
        is independent of how size itself is implemented.

        Note: the implementing class could override this
        method if there is a more efficient way to check
        for emptiness (eg if "size()" is expensive)
     */
    default boolean isEmpty(){
        return size() == 0;
    }

    /**
     * Get the value stored in position defined by "index".
     *
     * Throw an exception if index is invalid
     */
    T get(int index);


    /**
     * Add a new value to the list, in the smaller available index (starting from 0).
     * Ie, append the value at the end of the list.
     */
    default void add(T value){
        /*
            Although an interface cannot have instance state, it can have implementation
            of methods. Those need to be declared with "default", and can call the other
            methods in this interface.
            In this particular example, adding at the end of the list is equivalent to
            add at position equal to the size of list itself.
            For example, appending to an empty list is the same as adding at position 0.
         */
        add(size(), value);
    }


    /**
     * Add a new value to the list, at the specified index position.
     *
     * Throw an exception if index is invalid
     */
    void add(int index, T value);

    /**
     * Get how many elements this collection has.
     */
    int size();
}
