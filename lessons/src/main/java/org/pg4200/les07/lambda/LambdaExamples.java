package org.pg4200.les07.lambda;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class LambdaExamples {

    public static void useRunnable(Runnable runnable){

        System.out.println("Before runnable");
        runnable.run();
        System.out.println("After runnable");
    }

    public static void useConsumer(Consumer<String> consumer){

        String foo = "foo";

        System.out.println("Before consumer");
        consumer.accept(foo);
        System.out.println("After consumer");
    }

    public static String usePredicate(Predicate<String> predicate){

        String foo = "foo";

        if(predicate.test(foo)){
            return foo;
        } else {
            return null;
        }
    }

    public static int useFunction(Function<String, Integer> function){

        String input = "foo";

        return function.apply(input);
    }


}
