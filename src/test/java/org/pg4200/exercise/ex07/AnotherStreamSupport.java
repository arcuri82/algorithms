package org.pg4200.exercise.ex07;

import org.pg4200.datastructure.set.HashMapSet;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class AnotherStreamSupport {

    public static <T, C extends Iterable<T>> AnotherStream<T> createStream(C collection) {
        return new Pipeline<T, T>(collection.iterator());
    }


    protected static class Pipeline<IN, OUT>  implements AnotherStream<OUT> {

        protected final Iterator<OUT> iterator;
        protected final Pipeline previousStage;
        protected final int depth;

        protected Pipeline(Iterator<OUT> iterator) {
            this.iterator = iterator;
            this.previousStage = null;
            this.depth = 0;
        }

        protected Pipeline(Pipeline previousStage) {
            this.iterator = previousStage.iterator;
            this.previousStage = previousStage;
            this.depth = previousStage.depth + 1;
        }

        protected Consumer<IN> opWrapConsumer(Consumer<OUT> consumer) {
            throw new IllegalStateException();
        }

        protected <T> Consumer<T> wrapConsumer(Consumer<OUT> consumer) {
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
        public int count() {

            int[] counter = {0};

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {
                    counter[0]++;
                }
            };

            Consumer<OUT> chain = wrapConsumer(collectingConsumer);

            Iterator<OUT> iter = iterator();

            while (iter.hasNext()) {
                OUT element = iter.next();
                chain.accept(element);
            }

            return counter[0];
        }

        @Override
        public AnotherStream<OUT> distinct() {
            return new Pipeline<OUT, OUT>(this) {

                @Override
                public Consumer<OUT> opWrapConsumer(Consumer<OUT> consumer) {

                    return new ChainedReference<OUT, OUT>(consumer) {

                        private final HashMapSet<OUT> values = new HashMapSet<>();

                        @Override
                        public void accept(OUT u) {
                            if(!values.isPresent(u)) {
                                values.add(u);
                                downstream.accept(u);
                            }
                        }
                    };
                }
            };
        }

        @Override
        public AnotherStream<OUT> skip(int n) {

            return new Pipeline<OUT, OUT>(this) {

                @Override
                public Consumer<OUT> opWrapConsumer(Consumer<OUT> consumer) {

                    return new ChainedReference<OUT, OUT>(consumer) {

                        private int counter = 0;
                        private final int skip = n;

                        @Override
                        public void accept(OUT u) {
                            if(counter >= skip){
                                downstream.accept(u);
                            }
                            counter++;
                        }
                    };
                }
            };
        }
    }

    protected static abstract class ChainedReference<T, OUT> implements Consumer<T> {

        protected final Consumer<OUT> downstream;

        public ChainedReference(Consumer<OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }
    }
}
