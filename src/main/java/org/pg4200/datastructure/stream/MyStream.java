package org.pg4200.datastructure.stream;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public interface MyStream<T> {

    Iterator<T> iterator();

    MyStream<T> filter(Predicate<T> predicate);

    <R> MyStream<R> map(Function<T, R> mapper);

    <R> MyStream<R> flatMap(Function<T, MyStream<R>> mapper);

    void forEach(Consumer<T> action);

    StreamList<T> collectToList();
}
