package org.pg4200.exercise.ex07;

import java.util.Iterator;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public interface AnotherStream<T> {

    Iterator<T> iterator();

    /**
     * Terminal operation. Count number
     * of elements in the stream.
     */
    int count();

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
}
