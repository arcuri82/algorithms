package org.pg4200.ex07;

/**
 * Created by arcuri82 on 07-Jun-19.
 */

import org.pg4200.les02.list.MyList;
import org.pg4200.les06.set.MySet;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface  ExtendedList<T> extends MyList<T> {

    /**
     * Return a new list which contains all the values of the current
     * list for which predicate is true
     */
    ExtendedList<T> filter(Predicate<T> predicate);

    /**
     * Return a new list whose content is the result of the mapping function
     * on each element of the current list, in order
     */
    <R> ExtendedList<R> map(Function<T, R> mapper);

    /**
     *  Return a new list whose content is the merging of all the lists coming from the results
     *  of the mapping function applied on each element of the current list.
     */
    <R> ExtendedList<R> flatMap(Function<T, ExtendedList<R>> mapper);

    /**
     * On each element in the list, do apply the given action
     */
    void forEach(Consumer<T> action);

    /**
     * Create a new set with the values contained in this list
     */
    MySet<T> toSet();
}
