package org.pg4200.datastructure.stream;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class MyStreamSupport {

    public static <T, C extends Iterable<T>> MyStream<T> createStream(C collection) {
        return new Pipeline<T, T>(collection.iterator());
    }


    private static class Pipeline<IN, OUT> implements MyStream<OUT> {

        private final Iterator<OUT> iterator;
        private final Pipeline previousStage;
        private final int depth;

        private Pipeline(Iterator<OUT> iterator) {
            this.iterator = iterator;
            this.previousStage = null;
            this.depth = 0;
        }

        private Pipeline(Pipeline previousStage) {
            this.iterator = previousStage.iterator;
            this.previousStage = previousStage;
            this.depth = previousStage.depth + 1;
        }

        protected Consumer<IN> opWrapConsumer(Consumer<OUT> consumer) {
            throw new IllegalStateException();
        }

        private <T> Consumer<T> wrapConsumer(Consumer<OUT> consumer) {
            Objects.requireNonNull(consumer);

            Pipeline p = this;

            while (p.depth > 0) {
                consumer = p.opWrapConsumer(consumer);
                p = p.previousStage;
            }

            return (Consumer<T>) consumer;
        }


        @Override
        public Iterator<OUT> iterator() {
            return iterator;
        }

        @Override
        public MyStream<OUT> filter(Predicate<OUT> predicate) {

            Objects.requireNonNull(predicate);

            return new Pipeline<OUT, OUT>(this) {

                @Override
                public Consumer<OUT> opWrapConsumer(Consumer<OUT> consumer) {

                    return new ChainedReference<OUT, OUT>(consumer) {
                        @Override
                        public void accept(OUT u) {
                            if (predicate.test(u)) {
                                downstream.accept(u);
                            }
                        }
                    };
                }
            };
        }

        @Override
        public <R> MyStream<R> map(Function<OUT, R> mapper) {
            Objects.requireNonNull(mapper);

            return new Pipeline<OUT, R>(this) {

                @Override
                public Consumer<OUT> opWrapConsumer(Consumer<R> consumer) {

                    return new ChainedReference<OUT, R>(consumer) {
                        @Override
                        public void accept(OUT u) {
                            downstream.accept(mapper.apply(u));
                        }
                    };
                }
            };
        }

        @Override
        public <R> MyStream<R> flatMap(Function<OUT, MyStream<R>> mapper) {
            Objects.requireNonNull(mapper);

            return new Pipeline<OUT, R>(this) {

                @Override
                public Consumer<OUT> opWrapConsumer(Consumer<R> consumer) {

                    return new ChainedReference<OUT, R>(consumer) {
                        @Override
                        public void accept(OUT u) {
                            MyStream<R> result = mapper.apply(u);

                            if (result != null) {
                                result.forEach(downstream);
                            }
                        }
                    };
                }
            };
        }


        //----- terminal operations -----------------------------------------------

        @Override
        public void forEach(Consumer<OUT> action) {

            Consumer<OUT> chain = wrapConsumer(action);

            Iterator<OUT> iter = iterator();

            while (iter.hasNext()) {
                OUT element = iter.next();
                chain.accept(element);
            }
        }

        @Override
        public StreamList<OUT> collectToList() {

            StreamList<OUT> list = new StreamList<>();

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {
                    list.add(out);
                }
            };

            Consumer<OUT> chain = wrapConsumer(collectingConsumer);

            Iterator<OUT> iter = iterator();

            while (iter.hasNext()) {
                OUT element = iter.next();
                chain.accept(element);
            }

            return list;
        }


        // ----- private methods -------------------------------------------
    }

    private static abstract class ChainedReference<T, OUT> implements Consumer<T> {

        protected final Consumer<OUT> downstream;

        public ChainedReference(Consumer<OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }
    }
}
