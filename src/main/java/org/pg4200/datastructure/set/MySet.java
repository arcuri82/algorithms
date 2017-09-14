package org.pg4200.datastructure.set;

/**
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
