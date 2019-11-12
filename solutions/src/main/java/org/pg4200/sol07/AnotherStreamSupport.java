package org.pg4200.sol07;

import org.pg4200.ex07.AnotherStream;
import org.pg4200.les06.set.MySetHashMap;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class AnotherStreamSupport {

    public static <T, C extends Iterable<T>> AnotherStream<T> createStream(C collection) {
        return new Pipeline<T, T, T>(collection.iterator());
    }


    protected static class Pipeline<IN, OUT, T>  implements AnotherStream<OUT> {

        protected final Iterator<T> iterator;
        protected final Pipeline previousStage;
        protected final int depth;

        protected Pipeline(Iterator<T> iterator) {
            this.iterator = iterator;
            this.previousStage = null;
            this.depth = 0;
        }

        protected Pipeline(Pipeline previousStage) {
            this.iterator = previousStage.iterator;
            this.previousStage = previousStage;
            this.depth = previousStage.depth + 1;
        }

        protected ChainedReference<IN, OUT> chainConsumerToCurrentPipe(Consumer<OUT> consumer) {
            throw new IllegalStateException();
        }

        protected Consumer<T> chainAllConsumersInThePipeline(Consumer<OUT> consumer) {
            Objects.requireNonNull(consumer);

            Pipeline p = this;

            while (p.depth > 0) {
                consumer = p.chainConsumerToCurrentPipe(consumer);
                p = p.previousStage;
            }

            return (Consumer<T>) consumer;
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

            Consumer<T> chain = chainAllConsumersInThePipeline(collectingConsumer);

            while (iterator.hasNext()) {
                T element = iterator.next();
                chain.accept(element);
            }

            return counter[0];
        }

        @Override
        public String joinToString(String separator) {

            /*
                Note: code here could be simplified with StringJoiner
             */
            StringBuffer buffer = new StringBuffer();
            boolean[] first = {true};

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {

                    if(!first[0]){
                        buffer.append(separator);
                    } else {
                        first[0] = false;
                    }

                    String value = "";
                    if(out != null){
                        value = out.toString();
                    }
                    buffer.append(value);
                }
            };

            Consumer<T> chain = chainAllConsumersInThePipeline(collectingConsumer);

            while (iterator.hasNext()) {
                T element = iterator.next();
                chain.accept(element);
            }

            return buffer.toString();
        }

        @Override
        public boolean any(Predicate<OUT> predicate) {

            if(predicate == null){
                return false;
            }

            boolean[] result = {false};

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {
                    result[0] = predicate.test(out);
                }
            };

            Consumer<T> chain = chainAllConsumersInThePipeline(collectingConsumer);

            while (iterator.hasNext()) {
                T element = iterator.next();
                chain.accept(element);

                if(result[0]){
                    //can stop as soon as we find one
                    return true;
                }
            }

            return false;
        }

        @Override
        public AnotherStream<OUT> distinct() {
            return new Pipeline<OUT, OUT, T>(this) {

                @Override
                public ChainedReference<OUT, OUT> chainConsumerToCurrentPipe(Consumer<OUT> consumer) {

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

                        private final MySetHashMap<OUT> values = new MySetHashMap<>();

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

            return new Pipeline<OUT, OUT, T>(this) {

                @Override
                public ChainedReference<OUT, OUT> chainConsumerToCurrentPipe(Consumer<OUT> consumer) {

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

        @Override
        public AnotherStream<OUT> sorted() {

            List<OUT> list = new ArrayList<>();

            /*
                Custom action in which we add all inputs to a
                given list.
             */
            Consumer<OUT> collectingConsumer = out -> list.add(out);

            Consumer<T> chain = chainAllConsumersInThePipeline(collectingConsumer);

            while (iterator.hasNext()) {
                T element = iterator.next();
                chain.accept(element);
            }

            //this might throw an exception if elements do not implement Comparable
            Collections.sort(list, (a, b) -> ((Comparable)a).compareTo(b));

            /*
                we could have used streamList directly instead of a Java API list.
                Problems is, that then we would had needed our own function to sort the
                AnotherStreamList instead of relying on the existing Collections.sort
             */
            AnotherStreamList<OUT> streamList = new AnotherStreamList<>();
            for(OUT value : list){
                streamList.add(value);
            }

            return streamList.stream();
        }
    }

    protected static abstract class ChainedReference<T, OUT> implements Consumer<T> {

        protected final Consumer<OUT> downstream;

        public ChainedReference(Consumer<OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }
    }
}
