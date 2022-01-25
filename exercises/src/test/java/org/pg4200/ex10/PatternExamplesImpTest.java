package org.pg4200.ex10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatternExamplesImpTest extends PatternExamplesTestTemplate{
    @Override
    protected PatternExamplesImp getNewInstance() {
        return new PatternExamplesImp();
    }

    @Test
    public void testTopic(){
        String regex = getNewInstance().topic();

        assertFalse("".matches(regex));
        assertFalse("But what if we don't mention them at all?".matches(regex));
        assertFalse("Or what if we have algdat, but we end with no punctuation".matches(regex));
        assertFalse("Or we have algdat, but different punctuation;".matches(regex));

        assertTrue("This is about algdat.".matches(regex));
        assertTrue("This is about algorithms aka pg4200.".matches(regex));
        assertTrue("Is this about more than one algdat?".matches(regex));

        assertTrue("Algorithms are about more than one algorithm?".matches(regex) ||
                "algorithms are about more than one algorithm?".matches(regex));
        assertTrue("This is about pg4200!".matches(regex));

        assertTrue("Algorithms are tolerably interesting.".matches(regex) ||
                "algorithms are tolerably interesting.".matches(regex));
        assertTrue("Shouldn't we mention algorithms?".matches(regex) ||
                "Should we not we mention algorithms?".matches(regex));

    }

    @Test
    public void testEx1a(){
        String regex = getNewInstance().ex1a();

        assertFalse("".matches(regex));
        assertFalse("pg4201_00000.zip".matches(regex));
        assertFalse("pg4200_ab000.zip".matches(regex));
        assertFalse("pg4200_00000.rar".matches(regex));
        assertFalse("pg4200_00000zip".matches(regex));

        assertTrue("pg4200_00000.zip".matches(regex));
        assertTrue("pg4200_12345.zip".matches(regex));
        assertTrue("pg4200_12346.zip".matches(regex));
    }
}
