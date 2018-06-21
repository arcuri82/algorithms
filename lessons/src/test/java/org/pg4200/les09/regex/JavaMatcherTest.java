package org.pg4200.les09.regex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaMatcherTest {


    @Test
    public void testSameWord(){

        String regex = "foo";

        assertTrue(regex.matches(regex));
    }

    @Test
    public void testSingleLowerCaseLetter(){

        String regex = "[a-z]";

        assertTrue("a".matches(regex));
        assertTrue("b".matches(regex));
        assertTrue("c".matches(regex));

        assertFalse("H".matches(regex));
        assertFalse("hello".matches(regex));
    }

    @Test
    public void testOnlyLowerCaseLetters(){

        String regex = "[a-z]+";

        assertTrue("hello".matches(regex));
        assertFalse("Hello".matches(regex));
    }

    @Test
    public void testOnlyLettersRegardlessOfCase(){

        String regex = "[a-zA-Z]+";

        assertTrue("hello".matches(regex));
        assertTrue("Hello".matches(regex));

        assertFalse("Hello there".matches(regex));
    }

    @Test
    public void testOnlyVowels(){

        String regex = "[aeiouAEIOU]+";

        assertTrue("a".matches(regex));
        assertTrue("eeeeiii".matches(regex));
        assertTrue("Ou".matches(regex));
        assertTrue("AaaaaaA".matches(regex));

        assertFalse("b".matches(regex));
        assertFalse("ab".matches(regex));
    }

    @Test
    public void testAnyWordStartingWithF(){

        String regex = "F[a-zA-Z]*";

        assertTrue("F".matches(regex));
        assertTrue("Foo".matches(regex));

        assertFalse("oo".matches(regex));
        assertFalse("foo".matches(regex));
        assertFalse("Foo1".matches(regex));
    }


    @Test
    public void testTwoWordsSingleSpace(){

        String regex = "[a-zA-Z]+ [a-zA-Z]+";

        assertFalse("hello".matches(regex));
        assertFalse("Hello".matches(regex));

        assertTrue("Hello there".matches(regex));
        assertFalse("Hello     there".matches(regex));
        assertFalse("Hello there sir".matches(regex));
    }

    @Test
    public void testAnyWordWithAnySpace(){

        String regex = "[a-zA-Z ]+";

        assertTrue("hello".matches(regex));
        assertTrue("Hello".matches(regex));

        assertTrue("Hello there".matches(regex));
        assertTrue("Hello     there".matches(regex));
        assertTrue("Hello there sir".matches(regex));
    }


    @Test
    public void testPositiveNumber(){

        String regex = "[1-9]+";

        assertFalse("".matches(regex));
        assertFalse("a".matches(regex));
        assertFalse("1a".matches(regex));
        assertFalse("-1".matches(regex));

        assertTrue("1".matches(regex));
        assertTrue("47".matches(regex));
        assertTrue("319".matches(regex));
        assertTrue("9958".matches(regex));
        assertTrue("1234567".matches(regex));
    }

    @Test
    public void testOneToFourDigits(){

        String regex = "[1-9]{1,4}";

        assertFalse("".matches(regex));
        assertFalse("a".matches(regex));
        assertFalse("1a".matches(regex));
        assertFalse("1234567".matches(regex));

        assertTrue("1".matches(regex));
        assertTrue("47".matches(regex));
        assertTrue("319".matches(regex));
        assertTrue("9958".matches(regex));
    }


    @Test
    public void testAnyNumber(){

        String regex = "-?[1-9]+";

        assertFalse("".matches(regex));
        assertFalse("a".matches(regex));
        assertFalse("1a".matches(regex));
        assertFalse("--1".matches(regex));
        assertFalse("1-".matches(regex));
        assertFalse("1-2".matches(regex));

        assertTrue("-1".matches(regex));
        assertTrue("-22".matches(regex));
        assertTrue("1".matches(regex));
        assertTrue("47".matches(regex));
        assertTrue("319".matches(regex));
        assertTrue("9958".matches(regex));
        assertTrue("1234567".matches(regex));
    }


    @Test
    public void testParentheses(){

        String regex = "(ab)|(cd)";

        assertFalse("(ab)|(cd)".matches(regex));
        assertFalse("abcd".matches(regex));

        assertTrue("ab".matches(regex));
        assertTrue("cd".matches(regex));
    }

    @Test
    public void testEscapedMetaCharacters(){

        String regex = "\\(ab\\)\\|\\(cd\\)";

        assertTrue("(ab)|(cd)".matches(regex));

        assertFalse("abcd".matches(regex));
        assertFalse("ab".matches(regex));
        assertFalse("cd".matches(regex));
    }


    @Test
    public void testWordsInParenthesesSeparatedByVerticalLine(){

        String regex = "\\([a-zA-Z]+\\)(\\|\\([a-zA-Z]+\\))*";

        assertFalse("()".matches(regex));
        assertFalse("(a)(b)".matches(regex));
        assertFalse("(a)||(b)".matches(regex));
        assertFalse("|(a)|(b)".matches(regex));

        assertTrue("(a)".matches(regex));
        assertTrue("(ab)".matches(regex));
        assertTrue("(a)|(b)".matches(regex));
        assertTrue("(a)|(b)|(cd)".matches(regex));
    }

    @Test
    public void testOrNoParentheses(){

        String regex = "ab|c";

        assertFalse("ac".matches(regex));

        assertTrue("ab".matches(regex));
        assertTrue("c".matches(regex));
    }
}
