package org.pg4200.datastructure.stream;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * We can base our implementation of streams based on iterators.
 * So, we can easily add streams to any data structure as long as
 * it is Iterable
 *
 * Created by arcuri82 on 03-Oct-17.
 */
public class MyStreamSupport {

    public static <T, C extends Iterable<T>> MyStream<T> createStream(C collection) {
        return new Pipeline<T, T>(collection.iterator());
    }

    /*
        A pipeline is a stream.
        A pipeline will take something as input of type IN, and output
        something of type OUT.
        IN and OUT can be of exactly the same type, they do not have
        to be necessarily different.
        From the point of view of Stream though, we are only interested
        in the output.
     */
    protected static class Pipeline<IN, OUT> implements MyStream<OUT> {

        /**
         * Iterator over the elements of the collection
         */
        protected final Iterator<OUT> iterator;

        /**
         * A potential previous stage of the stream.
         * Eg, when we pipeline different streams together
         * to apply transformation and filtering
         */
        protected final Pipeline previousStage;

        /**
         * The depth of pipelined streams, ie the distance
         * in stream pipes from the source collection.
         */
        protected final int depth;

        /**
         * Main constructor, called directly on the collection
         */
        protected Pipeline(Iterator<OUT> iterator) {
            this.iterator = iterator;
            this.previousStage = null;
            this.depth = 0;
        }

        /**
         * Secondary constructor used to pipeline different streams
         */
        protected Pipeline(Pipeline previousStage) {
            this.iterator = previousStage.iterator;
            this.previousStage = previousStage;
            this.depth = previousStage.depth + 1;
        }

        protected Consumer<IN> opWrapConsumer(Consumer<OUT> consumer) {
            throw new IllegalStateException();
        }

        /**
         * Method called by a terminal operation to actually connect all the streams
         * in the previous stages.
         */
        protected <T> Consumer<T> wrapConsumer(Consumer<OUT> consumer) {
            Objects.requireNonNull(consumer);

            Pipeline p = this;

            /*
                We start from last stream (ie the terminal operation),
                which has the highest depth, and we go backward to the
                source, pipelining all those streams in between.
                How they are actually pipelined depend on the kind
                of stream, which is defined in the opWrapConsumer
                method.
                This opWrapConsumer must be overridden for all streams
                but the source.
             */

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

            /*
                A filter does not change the type of the streamed values,
                so IN == OUT.

                Note: here I am creating "anonymous" classes in which I am
                overriding methods on the fly.
             */
            return new Pipeline<OUT, OUT>(this) {

                @Override
                public Consumer<OUT> opWrapConsumer(Consumer<OUT> consumer) {

                    return new ChainedReference<OUT, OUT>(consumer) {

                        /*
                            note: the "consumer" input to the constructor
                            of ChainedReference will be stored in the variable
                            "downstream".
                         */

                        @Override
                        public void accept(OUT u) {
                            /*
                                Propagate the input value to the downstream pipe
                                only if the predicate is satisfied
                             */
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

            /*
                In the new stream, we are changing the type.
                The output OUT of the current stream becomes
                the input of this stream (so OUT in <OUT,R> is in the first
                position), whereas its output is going to be of type R.
             */

            return new Pipeline<OUT, R>(this) {

                @Override
                public Consumer<OUT> opWrapConsumer(Consumer<R> consumer) {

                    return new ChainedReference<OUT, R>(consumer) {
                        @Override
                        public void accept(OUT u) {
                            /*
                                This stream always propagate to the downstream.
                                However, the values in input are transformed
                                from OUT to R based on the given mapper.
                             */
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
                            /*
                                Applying the mapper to the given input
                                produces a new stream.
                                From this stream, we directly call the terminal
                                operation "forEach" to get all of its values,
                                and stream all of them to the downstream.
                             */
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

            /*
                make sure all the streams are chained.
                Ie, do not apply "action" directly on each element,
                as it has to be chained with all previous transformation.
             */
            Consumer<OUT> chain = wrapConsumer(action);

            Iterator<OUT> iter = iterator();

            while (iter.hasNext()) {
                /*
                    On each element in the collection,
                    apply the chained consumers of all chained
                    streams.
                 */
                OUT element = iter.next();
                chain.accept(element);
            }
        }

        @Override
        public StreamList<OUT> collectToList() {

            StreamList<OUT> list = new StreamList<>();

            /*
                Custom action in which we add all inputs to a
                given list.
             */
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

    }

    /**
     *  A special kind of consumer which is chained with another one
     */
    protected static abstract class ChainedReference<T, OUT> implements Consumer<T> {

        protected final Consumer<OUT> downstream;

        public ChainedReference(Consumer<OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }
    }
}
