package org.pg4200.sol01;

import org.junit.jupiter.api.Test;
import org.pg4200.ex01.TriangleClassification.Classification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.pg4200.ex01.TriangleClassification.Classification.*;
import static org.pg4200.ex01.TriangleClassification.classify;

public class TriangleClassificationTest {

    @Test
    public void testNegativeValue() {
        Classification res = classify(-2, 4, 4);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testEquilateral(){
        Classification res = classify(4, 4, 4);
        assertEquals(EQUILATERAL, res);
    }

    @Test
    public void testIsosceles(){
        Classification res = classify(3, 4, 4);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testTooLongEdge(){
        Classification res = classify(200, 4, 4);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testScalene(){
        Classification res = classify(3, 4, 5);
        assertEquals(SCALENE, res);
    }

}
