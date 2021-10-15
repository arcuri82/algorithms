package org.pg4200.ex08;

import org.pg4200.les08.stream.MyStreamSupport;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class AnotherExampleStreamSupport {

    public static <T, C extends Iterable<T>> AnotherStream<T> createStream(C collection){
        return new Pipeline<T, T, T>(collection.iterator());
    }

    protected static abstract class ChainedReference<IN, OUT> implements Consumer<IN> {

        protected final Consumer<OUT> downstream;

        public ChainedReference(Consumer<OUT> downstream) {
            this.downstream = Objects.requireNonNull(downstream);
        }
    }

    protected static class  Pipeline<IN, OUT, T> implements AnotherStream<OUT>{
        private final Iterator<T> iterator;
        private final Pipeline<?, IN, T> previousStage;
        private final int depth;

        public Pipeline(Iterator<T> iterator){
            this.iterator = iterator;
            this.previousStage = null;
            this.depth = 0;
        }

        public Pipeline(Pipeline section){
            this.iterator = section.iterator;
            this.previousStage = section;
            this.depth = section.depth + 1;
        }

        @Override
        public int count() {
            int[] count = {0};

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {
                    count[0]++;
                }
            };

            Consumer<T> chain = chainAllConsumersInThePipeline(collectingConsumer);

            while (iterator.hasNext()){
                T element = iterator.next();
                chain.accept(element);
            }

            return count[0];
        }

        private Consumer<T> chainAllConsumersInThePipeline(Consumer<OUT> consumer) {
            Objects.requireNonNull(consumer);

            Pipeline p  = this;

            while (p.depth > 0){
                consumer = p.chainConsumerToCurrentPipe(consumer);
                p = p.previousStage;
            }

            return (Consumer<T>) consumer;
        }

        protected ChainedReference<IN, OUT> chainConsumerToCurrentPipe(Consumer<OUT> consumer) {
            throw new IllegalStateException();
        }

        @Override
        public String joinToString(String separator) {
            return null;
        }

        @Override
        public boolean any(Predicate<OUT> predicate) {
            return false;
        }

        @Override
        public Optional<OUT> reduce(BinaryOperator<OUT> accumulator) {
            return Optional.empty();
        }

        @Override
        public AnotherStream<OUT> distinct() {

            AnotherExampleStreamList<OUT> elementsSoFar = new AnotherExampleStreamList<>();

            Consumer<OUT> collectingConsumer = new Consumer<OUT>() {
                @Override
                public void accept(OUT out) {
                    if(!elementsSoFar.contains(out)){
                        elementsSoFar.add(out);
                    }
                }
            };


            Consumer<T> chain = chainAllConsumersInThePipeline(collectingConsumer);

            while (iterator.hasNext()){
                T element = iterator.next();
                chain.accept(element);
            }

            return elementsSoFar.stream();
        }

        @Override
        public AnotherStream<OUT> skip(int n) {
            return null;
        }

        @Override
        public AnotherStream<OUT> sorted() {
            return null;
        }
    }
}
