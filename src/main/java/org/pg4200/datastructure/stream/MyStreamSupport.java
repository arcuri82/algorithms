package org.pg4200.datastructure.stream;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class MyStreamSupport {

    public static <T, C extends Iterable<T>> MyStream<T> createStream(C collection){
        return new MyStreamImp<>(collection.iterator());
    }


    private static class  MyStreamImp<T> implements MyStream<T>{

        private final Iterator<T> iterator;

        private MyStreamImp(Iterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public Iterator<T> iterator() {
            return iterator;
        }

        @Override
        public Stream<T> filter(Predicate<? super T> predicate) {
            return null;
        }

        @Override
        public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
            return null;
        }

        @Override
        public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
            return null;
        }

        @Override
        public void forEach(Consumer<? super T> action) {

        }

        @Override
        public StreamList<T> collectToList() {
            return null;
        }
    }
}
