package org.pg4200.ex03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class SortCheckerTestTemplate {

    protected abstract SortChecker getNewInstance();

    private SortChecker checker;

    @BeforeEach
    public void init(){
        checker = getNewInstance();
    }

    @Test
    public void testBothNull(){
        boolean ok = checker.isSortedCopy(null, null);
        assertTrue(ok);
    }

    @Test
    public void testOneNull(){
        boolean ok = checker.isSortedCopy(new String[1], null);
        assertFalse(ok);
    }

    @Test
    public void testLengthMismatch(){
        Integer[] original = {1};
        Integer[] sorted = {1,2};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertFalse(ok);
    }

    @Test
    public void testNullElementInOriginal(){
        Integer[] original = {1,null};
        Integer[] sorted = {1,2};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertFalse(ok);
    }

    @Test
    public void testNullElementInSorted(){
        Integer[] original = {1,2};
        Integer[] sorted = {1,null};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertFalse(ok);
    }

    @Test
    public void testNotSorted(){
        Integer[] original = {1,2,3};
        Integer[] sorted = {3,2,1};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertFalse(ok);
    }

    @Test
    public void testSortedButNotPermutation(){
        Integer[] original = {1,2,3};
        Integer[] sorted = {4,5,6};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertFalse(ok);
    }

    @Test
    public void testSortedButWrongRepetitions(){
        Integer[] original = {1,2,2};
        Integer[] sorted = {1,1,2};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertFalse(ok);
    }

    @Test
    public void testEmpty(){
        Integer[] original = {};
        Integer[] sorted = {};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertTrue(ok);
    }

    @Test
    public void testSingle(){
        Integer[] original = {1};
        Integer[] sorted = {1};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertTrue(ok);
    }

    @Test
    public void testSorted(){
        Integer[] original = {2,4,1,3};
        Integer[] sorted = {1,2,3,4};

        boolean ok = checker.isSortedCopy(original, sorted);
        assertTrue(ok);
    }

}
