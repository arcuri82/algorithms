package org.pg4200.datastructure.stream;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public interface MyStream<T> {

    Iterator<T> iterator();

    Stream<T> filter(Predicate<? super T> predicate);

    <R> Stream<R> map(Function<? super T, ? extends R> mapper);

    <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);

    void forEach(Consumer<? super T> action);

    StreamList<T> collectToList();
}
