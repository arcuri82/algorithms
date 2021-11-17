package org.pg4200.ex01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TriangleClassificationTest {

    @Test
    public void testEquilateral(){
        int a =  4;
        int b = 4;
        int c = 4;

        TriangleClassification.Classification res = TriangleClassification.classify(a, b, c);

        assertTrue(res == TriangleClassification.Classification.EQUILATERAL);
    }

    @Test
    public void testIsosceles(){
        int a = 7;
        int b = 7;
        int c = 5;
        genericTest(a, b, c, TriangleClassification.Classification.ISOSCELES);
    }

    @Test
    public void testScalene(){
        int a = 9;
        int b = 8;
        int c = 7;

        genericTest(a, b, c, TriangleClassification.Classification.SCALENE);
    }

    @Test
    public void testNope(){
        int a = 10;
        int b = 10;
        int c = 120;

        genericTest(a, b, c, TriangleClassification.Classification.NOT_A_TRIANGLE);
    }

    @Test
    public void testDoubleNope(){
        int a = -1;
        int b = 1;
        int c = 1;

        genericTest(a, b, c, TriangleClassification.Classification.NOT_A_TRIANGLE);
    }


    private void genericTest(int a, int b, int c, TriangleClassification.Classification classif){
        TriangleClassification.Classification res = TriangleClassification.classify(a, b, c);

        assertEquals(res, classif);
    }
}
