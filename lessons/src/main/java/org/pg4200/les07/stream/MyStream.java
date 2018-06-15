package org.pg4200.les07.stream;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Think about "streams" like enhanced iterators over a collection.
 * <p>
 * On a stream, we can apply transformation that can alter the data
 * in the stream, and how we retrieve such data.
 * This is achieved by pipelining different stream together.
 * <p>
 * A stream will be closed by a terminal operation, which will
 * specify what to do with the values coming from the stream.
 *
 * Note: here we are only having a few examples of methods available
 * for streams. The collections in the JDK API have many more.
 *
 * Created by arcuri82 on 03-Oct-17.
 */
public interface MyStream<T> {

    /**
     * Pipeline current stream into a new stream where elements are skipped
     * if they do not satisfy the given predicate
     */
    MyStream<T> filter(Predicate<T> predicate);

    /**
     * Pipeline current stream into a new stream where the input values of
     * type T are transformed into new values of type R, according to the
     * given mapping function.
     *
     * Note: the term "map" here is NOT related to the Map collection type.
     */
    <R> MyStream<R> map(Function<T, R> mapper);

    /**
     *  Pipeline current stream into a new stream where, for each input x
     *  coming from the current stream, a new stream of type R is opened,
     *  and all elements from such stream are propagated into the current
     *  stream.
     *  In other words, this is a way to flatten/combine k different streams
     *  into a single one.
     *
     *  Note: this is a bit tricky to grasp at first. Easier to understand
     *  it with some working examples.
     */
    <R> MyStream<R> flatMap(Function<T, MyStream<R>> mapper);

    /*
        Terminal Operations are what start the stream and retrieve
        all values from it.
      */

    /**
     *  For each value coming from the stream, execute the given action.
     */
    void forEach(Consumer<T> action);

    /**
     *  Create a list. Add all elements coming from the stream
     *  into this list.
     */
    MyStreamCollectionList<T> collectToList();
}
