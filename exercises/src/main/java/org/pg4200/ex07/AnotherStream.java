package org.pg4200.ex07;

import java.util.function.Predicate;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public interface AnotherStream<T> {

    /**
     * Terminal operation. Count number
     * of elements in the stream.
     */
    int count();

    /**
     *  On each object, its String representation is computed with toString().
     *  A null value will be considered as an empty string.
     *  All those strings are joined together into a single one, with "separator"
     *  between each of them (but not before the first, and not after the last).
     *
     *  For example, if the stream has x, y=null and z, and separator=",", then the final result
     *  would be the output of:
     *  x.toString()+","+""+","+z.toString()
     */
    String joinToString(String separator);

    /**
     *  Check if the given predicate is true for any element in the stream.
     */
    boolean any(Predicate<T> predicate);

    /**
     * Filtering operation that only let pass an element
     * once. Ie, if an element in the stream is
     * present more than once, it will be propagated to
     * the stream only once, the first time it is seen.
     */
    AnotherStream<T> distinct();

    /**
     * Filtering operation that does ignore and skip
     * the first n elements coming from the stream.
     */
    AnotherStream<T> skip(int n);

    /**
     * Sort the whole incoming stream, and propagate each value to the
     * downstream in such order.
     * Will throw an exception if elements are not implementing {@code Comparable<T>}
     */
    AnotherStream<T> sorted();
}
