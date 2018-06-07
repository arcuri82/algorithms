package org.pg4200.sol01;

import org.pg4200.ex01.ArrayUtils;

public class ArrayUtilsImp implements ArrayUtils {

    @Override
    public int min(int[] array) throws IllegalArgumentException {
        checkArray(array);

        int min = array[0];

        for(int i=1; i<array.length; i++){
            int val = array[i];
            if(val < min){
                min = val;
            }
        }
        return min;
    }

    @Override
    public int max(int[] array) throws IllegalArgumentException {
        checkArray(array);

        int max = array[0];

        for(int i=1; i<array.length; i++){
            int val = array[i];
            if(val > max){
                max = val;
            }
        }
        return max;
    }

    @Override
    public double mean(int[] array) throws IllegalArgumentException {
        checkArray(array);

        double sum = 0;

        for(int i=0; i<array.length; i++){
            sum += array[i];
        }
        return sum / array.length;
    }

    private void checkArray(int[] array){
        if(array == null || array.length == 0){
            throw new IllegalArgumentException("Invalid empty array as input");
        }
    }
}
