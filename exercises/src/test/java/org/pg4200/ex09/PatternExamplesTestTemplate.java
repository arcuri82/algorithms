package org.pg4200.ex09;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by arcuri82 on 07-May-18.
 */
public abstract class PatternExamplesTestTemplate {

    protected abstract PatternExamples getNewInstance();

    @Test
    public void testDna(){

        String regex = getNewInstance().dnaRegex();

        assertFalse("".matches(regex));
        assertFalse("z".matches(regex));
        assertFalse("a".matches(regex));
        assertFalse("Az".matches(regex));
        assertFalse("CAz".matches(regex));
        assertFalse(".".matches(regex));

        assertTrue("A".matches(regex));
        assertTrue("T".matches(regex));
        assertTrue("C".matches(regex));
        assertTrue("G".matches(regex));
        assertTrue("AT".matches(regex));
        assertTrue("AAATCGGTCGATTTC".matches(regex));
    }


    @Test
    public void testTelephoneNumber(){

        String regex = getNewInstance().telephoneNumberRegex();

        assertFalse("".matches(regex));
        assertFalse("1".matches(regex));
        assertFalse("1234567".matches(regex));
        assertFalse("123456789".matches(regex));
        assertFalse("00471234567".matches(regex));
        assertFalse("+47123456789".matches(regex));
        assertFalse("4712345678".matches(regex));

        assertTrue("12345678".matches(regex));
        assertTrue("12341234".matches(regex));
        assertTrue("99911122".matches(regex));
        assertTrue("004799911122".matches(regex));
        assertTrue("+4799911122".matches(regex));
    }


    @Test
    public void testEmail(){

        String regex = getNewInstance().emailRegex();

        assertFalse("a".matches(regex));
        assertFalse("a@a".matches(regex));
        assertFalse("a@com".matches(regex));
        assertFalse("a@a.a".matches(regex));
        assertFalse("a.@a.com".matches(regex));
        assertFalse(".a@a.com".matches(regex));
        assertFalse("a..a@a.com".matches(regex));
        assertFalse("a.a@a..com".matches(regex));
        assertFalse("a.a@?.com".matches(regex));

        assertTrue("a@a.com".matches(regex));
        assertTrue("a.a@a.com".matches(regex));
        assertTrue("a.123@a.com".matches(regex));
        assertTrue("a@a.co.uk".matches(regex));
        assertTrue("foo@gmail.com".matches(regex));
    }
}
