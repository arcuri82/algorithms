package org.pg4200.ex01;

public interface ArrayUtils {

    /**
     *
     * @param array
     * @return the minimum value in the array
     * @throws IllegalArgumentException if input is null or empty
     */
    int min(int[] array) throws IllegalArgumentException;

    /**
     *
     * @param array
     * @return the maximum value in the array
     * @throws IllegalArgumentException if input is null or empty
     */
    int max(int[] array) throws IllegalArgumentException;

    /**
     *
     * @param array
     * @return the arithmetic average of the values in the array
     * @throws IllegalArgumentException if input is null or empty
     */
    double mean(int[] array) throws IllegalArgumentException;
}
