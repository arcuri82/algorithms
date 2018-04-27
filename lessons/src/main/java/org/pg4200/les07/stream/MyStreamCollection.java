package org.pg4200.les07.stream;

/**
 * Just define an interface for classes on which we can open streams.
 *
 * Created by arcuri82 on 03-Oct-17.
 */
public interface MyStreamCollection<T> extends Iterable<T> {

    MyStream<T> stream();
}
