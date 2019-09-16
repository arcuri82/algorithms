package org.pg4200.ex01;

import org.pg4200.ex01.ArrayUtils;

class ArrayUtilsImp implements ArrayUtils {
    public int min(int[] array) throws IllegalArgumentException{

        if(array==null || array.length == 0){
            throw new IllegalArgumentException();
        }

        int valMin = array[0];
        for(int i=0; i<array.length; i++){
            int val1 = array[i];
            if(val1<valMin){
                valMin=array[i];
            }
        }
        return valMin;
    }

    public int max(int[] array) throws IllegalArgumentException{
        if(array==null || array.length == 0){
            throw new IllegalArgumentException();
        }
        int valMax =  array[0];
        for(int i=0; i<array.length; i++){
            int val1 = array[i];
            if(val1>valMax){
                valMax=array[i];
            }
        }
        return valMax;
    }

    public double mean(int[] array) throws IllegalArgumentException{
        if(array==null || array.length == 0){
            throw new IllegalArgumentException();
        }
        int sum=0;
        for(int i=0; i<array.length; i++){
            sum +=array[i];
        }
        return sum/array.length;
    }
}