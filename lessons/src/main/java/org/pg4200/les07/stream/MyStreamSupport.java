package org.pg4200.les07.stream;

// WARNING: this is one of the 12 classes you need to study and know by heart


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

    /**
     *  A stream can be created on any collection C of element types T,
     *  as long as C is implementing the interface Iterable< T >.
     */
    public static <T, C extends Iterable<T>> MyStream<T> createStream(C collection) {
        /*
            The first pipe take type T elements as input from the collection,
            and output them
         */
        return new Pipeline<T, T, T>(collection.iterator());
    }


    /**
     *  A special kind of consumer which is chained with another one.
     *  The idea is that the current consumer will take some input of type IN.
     *  Then, it will compute some output of type OUT.
     *  Such output will be given as input to the chained downstream consumer.
     *  As such consumer expects type OUT, it is declared as Consumer< OUT >.
     *  How the output is generated will depend on the code of the pipe, and
     *  so this class is abstract, as one should implement accept(IN) and not
     *  create instances directly of ChainedReference.
     */
    protected static abstract class ChainedReference<IN, OUT> implements Consumer<IN> {

        protected final Consumer<OUT> downstream;

        public ChainedReference(Consumer<OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }
    }

    /*
        A pipeline is a stream.
        A pipeline will take something as input of type IN, and output
        something of type OUT.
        IN and OUT can be of exactly the same type, they do not have
        to be necessarily different.
        From the point of view of Stream though, we are only interested
        in the output.
        However, we also need to keep track of the original type T
        of the collection we created the stream from.
     */
    protected static class Pipeline<IN, OUT, T> implements MyStream<OUT> {

        /**
         * A reference to an iterator on the original collection.
         * Note: the type of collection (eg String or Integer) could
         * have nothing to do with the current IN and OUT of the current
         * pipe, because types can be transformed/changed throughout the pipeline
         */
        protected final Iterator<T> iterator;

        /**
         * A potential previous stage of the stream.
         * Eg, when we pipeline different streams together
         * to apply transformation and filtering.
         * The output of previous pipeline is the input to the
         * current pipeline.
         * For the current pipeline, the input of the previous
         * is irrelevant
         */
        protected final Pipeline<?, IN, T> previousStage;

        /**
         * The depth of pipelined streams, ie the distance
         * of this pipe in the stream from the source collection.
         */
        protected final int depth;

        /**
         * Main constructor, called directly on the collection
         */
        protected Pipeline(Iterator<T> iterator) {
            this.iterator = iterator;
            this.previousStage = null;
            this.depth = 0;
        }

        /**
         * Secondary constructor used to pipe different streams.
         * Recall, the output of previous pipe is the input to the
         * current one
         */
        protected Pipeline(Pipeline<?, IN, T> previousStage) {
            this.iterator = previousStage.iterator;
            this.previousStage = previousStage;
            this.depth = previousStage.depth + 1;
        }


        /**
         * Method called by a terminal operation to actually connect all the streams
         * in the previous stages.
         *
         * What we get is a Consumer for the first stage of the pipe (reading elements
         * of type T directly on the collection) that, once called, will call all
         * the Consumers down the pipeline.
         */
        protected  Consumer<T> chainAllConsumersInThePipeline(Consumer<OUT> consumer) {
            Objects.requireNonNull(consumer);

            /*
                We start from last stream (ie the terminal operation),
                which is the current one.
                It has the highest/deepest depth value, and we go backward to the
                source, piping all those streams in between by
                following the previousStage reference.
                How they are actually pipelined depend on the kind
                of stream, which is defined in the chainConsumerToCurrentPipe
                method.
                This chainConsumerToCurrentPipe must be overridden for all streams
                but the source.

                Also notice the type _erasure_ here.
                Pipeline is not declared with generics <X,Y>.
                This important, as there can be many different type
                changes between IN and OUT in the whole pipeline.
                In this case, the compiler does not complain, and
                treats those as Object references.

                Side-effect though is that, in the end, we need to force
                a cast to Consumer<T> for the first consumer in the pipeline,
                reading data (of type T) directly from the collection
             */

            Pipeline p = this;

            while (p.depth > 0) {
                consumer = p.chainConsumerToCurrentPipe(consumer);
                p = p.previousStage;
            }

            /*
                Assume a pipeline
                A -> B -> C

                what we get back is a Consumer for A that, every time we call it on an input,
                it will call the Consumer for B.
                This latter will call the Consumer for C.
             */

            return (Consumer<T>) consumer;
        }


        /*
           The current pipe produces data of type OUT.
           To use such data from the pipe, there is going to be a Consumer
           of type OUT pulling such data.
           However, how and if such data should be sent to this consumer
           will depend on the characteristics of the pipe (eg if a filter,
           or a mapper).
           Point is, when the Consumer for OUT pulls data from current pipe,
           the current pipe needs to pull from its upstream (unless it is
           the source directly on the collection).
           So, here we need to create a Consumer representing the current pipe
           which will take data of type IN from the upstream.
           When such data is read, it will do some computation (depending on
           the kind of pipe), create some data of type OUT, and call the input
           consumer for OUT, creating a so called chain.
           How the chaining is done will depend on the kind of Stream,
           and so this method must be overridden.

           Note: this method is not abstract, and rather throw an exception if
           not overridden because the first pipe will have no chain with a previous
           one (and so does not need this method).
           See the use of Pipeline in createStream(), where it was instantiated directly.
        */
        protected ChainedReference<IN, OUT> chainConsumerToCurrentPipe(Consumer<OUT> consumer) {
            throw new IllegalStateException();
        }


        @Override
        public <R> MyStream<R> map(Function<OUT, R> mapper) {
            Objects.requireNonNull(mapper);

            /*
                In the new stream, we are changing the type.
                The output OUT of the current (this) stream becomes
                the input of the new stream (so OUT in <OUT,R> is in the first
                position), whereas its output is going to be of type R.

                Note: here I am creating "anonymous" classes in which I am
                overriding methods on the fly.

                The new stream takes as input IN the output OUT of the current stream.
                Its output will be some R, depending on the mapping function.
             */

            return new Pipeline<OUT, R, T>(this) {

                 /*
                    when we create concrete/non-abstract pipelines,
                    we need to override the method chainConsumerToCurrentPipe,
                    which will be used when adding a consumer to the output of this
                    pipe.

                    Note: this might be rather confusing.
                    We are inside a class called Pipeline, in which we are implementing
                    the method map().
                    However, inside such method, we are creating an anonymous class that
                    extends Pipeline itself, in which we override one of its methods...

                    This can create some confusion when looking at OUT and IN types.
                    Let's call the outer pipe X (the one on which map() is called on),
                    whereas the current anonymous one we are writing is Y.
                    This new pipe Y will take something as input and call the chained consumer
                    for its output type R.
                    But the input type is called OUT...
                    However, that OUT is what was defined for X, and Y takes as input
                    the output of X.
                 */

                @Override
                public ChainedReference<OUT, R> chainConsumerToCurrentPipe(Consumer<R> consumer) {

                    return new ChainedReference<OUT, R>(consumer) {
                        @Override
                        public void accept(OUT u) {
                            /*
                                This stream always propagates to the downstream.
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
        public MyStream<OUT> filter(Predicate<OUT> predicate) {

            Objects.requireNonNull(predicate);

            /*
                A filter does not change the type of the streamed values,
                so IN == OUT.

                Note: here I am creating "anonymous" classes in which I am
                overriding methods on the fly.
             */
            return new Pipeline<OUT, OUT, T>(this) {

                /*
                    when we create concrete/non-abstract pipelines,
                    we need to override the method chainConsumerToCurrentPipe,
                    which will be used when adding a consumer to the output of this
                    pipe.
                 */

                @Override
                public ChainedReference<OUT, OUT> chainConsumerToCurrentPipe(Consumer<OUT> consumer) {

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
        public <R> MyStream<R> flatMap(Function<OUT, MyStream<R>> mapper) {
            Objects.requireNonNull(mapper);

            return new Pipeline<OUT, R, T>(this) {

                @Override
                public ChainedReference<OUT, R> chainConsumerToCurrentPipe(Consumer<R> consumer) {

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
            Consumer<T> chain = chainAllConsumersInThePipeline(action);

            while (iterator.hasNext()) {
                /*
                    On each element in the collection,
                    apply the chained consumers of all chained
                    streams.
                 */
                T element = iterator.next();
                chain.accept(element);
            }
        }

        @Override
        public MyStreamCollectionList<OUT> collectToList() {

            MyStreamCollectionList<OUT> list = new MyStreamCollectionList<>();

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

            Consumer<T> chain = chainAllConsumersInThePipeline(collectingConsumer);

            while (iterator.hasNext()) {
                T element = iterator.next();
                chain.accept(element);
            }

            return list;
        }

    }
}
