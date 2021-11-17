package org.pg4200.ex01;

public class ArrayUtilsImp implements ArrayUtils {
    @Override
    public int min(int[] array) throws IllegalArgumentException {
        if (emptyOrNull(array)) throw new IllegalArgumentException("Shouldn't be null, now, should it?");
        int min = array[0];
        for (int i = 0; i < array.length; i++){
            if (array[i] < min) min = array[i];
        }

        return min;
    }

    @Override
    public int max(int[] array) throws IllegalArgumentException {
        if(emptyOrNull(array)) throw new IllegalArgumentException("Shouldn't be null for max either!");
        int max = array[0];
        for(int el : array){
            if (max < el) max = el;
        }

        return max;
    }

    @Override
    public double mean(int[] array) throws IllegalArgumentException {
        if (emptyOrNull(array)) throw new IllegalArgumentException("I mean it! It shouldn't be null");

        double res = 0.0;
        for(int el : array){
            res += el;
        }
        return res/array.length;
    }

    private boolean emptyOrNull(int[] array){
        return (array == null || array.length == 0);
    }
}
