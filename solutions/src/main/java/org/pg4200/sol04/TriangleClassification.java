package org.pg4200.sol04;

import static org.pg4200.sol04.TriangleClassification.Classification.*;

/**
 * Created by arcuri82 on 30-Apr-18.
 */
public class TriangleClassification {

    public enum Classification {NOT_A_TRIANGLE, ISOSCELES, SCALENE, EQUILATERAL}


    public static Classification classify(int a, int b, int c) {

        if(a <= 0 || b <= 0 || c <= 0){
            return NOT_A_TRIANGLE;
        }

        if(a==b && b==c){
            return EQUILATERAL;
        }

        int max = Math.max(a, Math.max(b, c));

        if ((max == a && max - b - c >= 0) ||
                (max == b && max - a - c >= 0) ||
                (max == c && max - a - b >= 0)) {
            return NOT_A_TRIANGLE;
        }

        if(a==b || a==c || b==c){
            return ISOSCELES;
        } else {
            return SCALENE;
        }
    }
}
