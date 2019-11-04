package org.pg4200.les05;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public interface MyMap<K extends Comparable<K>, V> {

    /**
     *   Create a mapping from the given Key to the given Value.
     *   If a mapping for Key already exists, replace the old
     *   value with this new one
     *
     * @throws NullPointerException if the {@code key} is null
     */
    void put(K key, V value);

    /**
     * Remove the given key from the container.
     *
     * @throws NullPointerException if the {@code key} is null
     */
    void delete(K key);

    /**
     *  Return the value in the container mapped by the given key
     *
     *  @throws NullPointerException if the {@code key} is null
     */
    V get(K key);

    /**
     *  The number of elements in the container
     */
    int size();

    /**
     *  Check if there is no element in the container
     */
    default boolean isEmpty(){
        return size() == 0;
    }
}
