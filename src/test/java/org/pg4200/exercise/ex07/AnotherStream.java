package org.pg4200.exercise.ex07;

import java.util.Iterator;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public interface AnotherStream<T> {

    Iterator<T> iterator();

    int count();

    AnotherStream<T> distinct();

    AnotherStream<T> skip(int n);
}
