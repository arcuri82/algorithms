package org.pg4200.les02.generic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-Jun-18.
 */
public class GenericExampleTest {

    @Test
    public void testIdentity() {

        GenericExample<String> example = new GenericExample<>();

        /*
            All classes are subclasses of Object.
            When using a type, we can use one of its subtype,
            eg String is a subtype of Object
         */

        String foo = "foo";

        Object x = example.identityObject(foo);
        assertNotNull(x);

        /*
            DOES NOT COMPILE.
            When we pass String as super-type Object, we lose
            info of String
         */
        //String y = example.identityObject(foo);

        /*
            We can force a casting if we are sure of the subtype.
            But this is "usually" not a good idea, as it could crash
            at runtime if the type is wrong.
            The compiler would not be able to help us here
         */
        String y = (String) example.identityObject(foo);
        assertNotNull(y);

        assertThrows(ClassCastException.class, () -> {
            /*
                Here we pass a String. What we get back is still a String,
                but we only see a reference to Object.
                We try to cast it to an Integer, but, because it is actually
                a String, it will throw an exception
             */
                    Integer w = (Integer) example.identityObject(foo);
                }
        );


        // by using Generics, we solve this issue
        String z = example.identityGeneric(foo);
        Integer w = example.identityGenericOnMethod("bar", 5);
        assertNotNull(z);
        assertNotNull(w);
    }


    @Test
    public void testMultiGenerics(){

        GenericExample<String> example = new GenericExample<>();

        //first has to be a String, but second could be anything
        GenericExample.MyPair<String, Integer> pair =
                example.createPair("foo", 5);
        assertNotNull(pair);
        assertEquals("foo", pair.x);
        //note: primitives like 5 are of type "int", which is not an object.
        //casting here does actually box the constant into an Integer object
        assertEquals((Integer)5, pair.y);

        GenericExample.MyPair<String, String> other =
                example.createPair("foo", "bar");
        assertNotNull(other);
        assertEquals("foo", other.x);
        assertEquals("bar", other.y);

        //This would not compile, as first element has to be a String
        //GenericExample.MyPair<Integer, String> notCompile =
        //        example.createPair(5, "bar");
    }


    @Test
    public void testExtend(){

        // "?" here means that we do not care of the generic T, as we
        // do not use it
        GenericExample<?> example = new GenericExample<>();

        Integer x = 5; //auto-boxing
        Integer y = 7;


        Comparable result = example.max(x, y);
        assertEquals(7, result);

        //Does not compile
        //Integer result = example.max(x, y);

        Integer resultWithGenerics = example.maxWithGenerics(x, y);
        assertEquals((Integer) 7, resultWithGenerics);

        /*
            Note the casting (boxing) of 7 above.
            Reason is that there is both:
            - assertEquals(int, int)
            - assertEquals(Object, Object)
            and the compiler would not be able to resolve which method
            to use when having a call on (int, Integer): should the compiler box
            the int to have (Integer, Integer)? or rather unbox Integer
            to have (int, int)? As it is ambiguous, we must do the boxing explicitly.
         */

        /*
            As we called the method with Integer, we can call it with any class
            that implements Comparable, like for example String.
         */
        String resultString = example.maxWithGenerics("foo", "bar");
        assertEquals("foo", resultString);
    }
}