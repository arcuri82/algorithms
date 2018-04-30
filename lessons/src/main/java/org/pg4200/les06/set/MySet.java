package org.pg4200.les06.set;

/**
 * In mathematics, a Set is a collection of unique elements,
 * for which there is no ordering defined, ie no positions
 * like in a List.
 *
 * Created by arcuri82 on 14-Sep-17.
 */
public interface MySet<E> {

    void add(E element);

    void remove(E element);

    boolean isPresent(E element);

    int size();

    default boolean isEmpty(){
        return size() == 0;
    }
}
