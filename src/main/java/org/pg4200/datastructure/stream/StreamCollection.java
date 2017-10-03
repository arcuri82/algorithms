package org.pg4200.datastructure.stream;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public interface StreamCollection<T> extends Iterable<T> {

    MyStream<T> stream();
}
