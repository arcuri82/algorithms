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

            /*
                We need to have an integer to represent the counter,
                ie the number of elements going through the stream
                without being blocked (eg by "filter" or "distinct"
                stream steps).
                A local int variable would live in the frame of this
                function call.
                However, it needs to be accessed in the frame of the
                call of the lambda expression (ie the Consumer).
                Java allows to read the outerscope variables, but not
                modify them.
                So, we need to pass a "reference" of the counter, as we would
                not modify the reference itself, but rather the value
                it points to.
                But Java has no references for primitive values (eg, int,
                double, boolean).
                So, to "simulate" a int reference, we just create an array
                of size 1.
             */
            int[] counter = {0};

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {
                    /*
                        Here we cannot modify "counter", but we are
                        allowed to modify "counter[i]".
                     */
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

                        /*
                            To check for uniqueness, we need to keep track
                            of all items seen so far.
                            To do that, we need to use a "collection".
                            But which one?
                            As we need to check for uniqueness, then it is best
                            to use a collection that enforces uniqueness of its
                            elements, so that we do not need to implement such
                            checks by ourselves.
                            Such a collection with these properties is for
                            example a "Set".
                         */

                        private final HashMapSet<OUT> values = new HashMapSet<>();

                        @Override
                        public void accept(OUT u) {
                            if(!values.isPresent(u)) {
                                /*
                                    If first time we see it, it is not in the
                                    local set.
                                    So we propagate downstream.
                                    However, we add it to the set, so that next
                                    time we see it coming in through the pipeline,
                                    we stop it (ie, not propagate downstream).
                                 */
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

                        /*
                            Keeping track of how many items we have seen so far.
                            We do not propagate downstream until such counter
                            becomes bigger than the number of items we want to "skip".
                         */
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
