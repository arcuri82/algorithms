package org.pg4200.sol04;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.pg4200.sol04.TriangleClassification.Classification.*;
import static org.pg4200.sol04.TriangleClassification.Classification;

/**
 * Created by arcuri82 on 30-Apr-18.
 */
public class TriangleClassificationTest {


    @Test
    public void testNegativeA(){
        Classification res = TriangleClassification.classify(-1, 1, 1);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testNegativeB(){
        Classification res = TriangleClassification.classify(1, -1, 1);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testNegativeC(){
        Classification res = TriangleClassification.classify(1, 1, -1);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testZeroA(){
        Classification res = TriangleClassification.classify(0, 1, 1);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testZeroB(){
        Classification res = TriangleClassification.classify(1, 0, 1);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testZeroC(){
        Classification res = TriangleClassification.classify(1, 1, 0);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testAllNegatives(){
        Classification res = TriangleClassification.classify(-1, -1, -1);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testAllZeros(){
        Classification res = TriangleClassification.classify(0, 0, 0);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testEquilateral(){
        Classification res = TriangleClassification.classify(1, 1, 1);
        assertEquals(EQUILATERAL, res);
    }

    @Test
    public void testLargeEquilateral(){
        int edge = Integer.MAX_VALUE;
        Classification res = TriangleClassification.classify(edge, edge, edge);
        assertEquals(EQUILATERAL, res);
    }

    @Test
    public void testIsoscelesAB(){
        Classification res = TriangleClassification.classify(2, 2, 1);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testIsoscelesAC(){
        Classification res = TriangleClassification.classify(2, 1, 2);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testIsoscelesBC(){
        Classification res = TriangleClassification.classify(1, 2, 2);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testTooLargeA(){
        Classification res = TriangleClassification.classify(5, 2, 2);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testTooLargeB(){
        Classification res = TriangleClassification.classify(2, 5, 2);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testTooLargeC(){
        Classification res = TriangleClassification.classify(2, 2, 5);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testLargeButShorterA(){
        int max = Integer.MAX_VALUE;
        Classification res = TriangleClassification.classify(max-1, max, max);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testLargeButShorterB(){
        int max = Integer.MAX_VALUE;
        Classification res = TriangleClassification.classify(max, max-1, max);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testLargeButShorterC(){
        int max = Integer.MAX_VALUE;
        Classification res = TriangleClassification.classify(max, max, max-1);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testScalene(){
        Classification res = TriangleClassification.classify(2, 3, 4);
        assertEquals(SCALENE, res);
    }

    @Test
    public void testLargeScalene(){
        int max = Integer.MAX_VALUE;
        Classification res = TriangleClassification.classify(max, max-1, max-2);
        assertEquals(SCALENE, res);
    }

}