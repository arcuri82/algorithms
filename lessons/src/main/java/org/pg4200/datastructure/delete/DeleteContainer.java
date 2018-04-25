package org.pg4200.datastructure.delete;

/**
 *  Before we defined a container interface to hold String objects.
 *  But what if I need to store more complex objects, like an hypothetical
 *  Song, PurchaseOrder, etc?
 *
 *  As the "type" of what we store is not important, we use Java "Generics"
 *
 * Created by arcuri82 on 15-Aug-17.
 */
public interface DeleteContainer<T> {

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
     * Add a new value to the collection, in the smaller available index (starting from 0).
     */
    void add(T value);

    /**
     * Get how many elements this collection has.
     */
    int size();
}
