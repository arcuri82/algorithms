package org.pg4200.les07.lambda;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class LambdaExamplesTest {


    @Test
    public void testRunnable() {

        Runnable anonymousClass = new Runnable() {
            @Override
            public void run() {
                System.out.println("Executing method run()");
            }
        };

        Runnable lambda = () -> System.out.println("Executing method run()");

        //they are all doing the same
        LambdaExamples.useRunnable(anonymousClass);
        LambdaExamples.useRunnable(lambda);
        LambdaExamples.useRunnable(() -> System.out.println("Executing method run()"));
    }

    @Test
    public void testConsumer() {

        Consumer<String> anonymousClass = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }
        };

        Consumer<String> lambda = s -> System.out.println(s.toUpperCase());

        //they are all doing the same
        LambdaExamples.useConsumer(anonymousClass);
        LambdaExamples.useConsumer(lambda);
        LambdaExamples.useConsumer(s -> System.out.println(s.toUpperCase()));
    }

    @Test
    public void testPredicate() {

        Predicate<String> anonymousClass = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        };

        Predicate<String> lambda = s -> s.toUpperCase().equals("FOO");

        //doing different things
        assertNull(LambdaExamples.usePredicate(anonymousClass));
        assertNotNull(LambdaExamples.usePredicate(lambda));
        assertNotNull(LambdaExamples.usePredicate(s -> {
                    //can also be a block of instructions
                    int length = s.length();
                    return length == 3;
                }
        ));
    }

    @Test
    public void testFunction(){

        Function<String, Integer> anonymousClass = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        Function<String, Integer> lambda = s -> 5;

        assertEquals(3, LambdaExamples.useFunction(anonymousClass));
        assertEquals(5, LambdaExamples.useFunction(lambda));
        assertEquals(6, LambdaExamples.useFunction(s -> s.length() * 2));
    }
}