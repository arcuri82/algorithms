package org.pg4200.ex03;

public interface SortChecker {

    /**
     * Check if "sorted" is a sorted copy of the
     * "original" array.
     *
     * Sorting is undefined for "null" elements.
     * Therefore, if any of the input array contains null values,
     * this method returns false.
     */
    <T extends Comparable<T>> boolean isSortedCopy(T[] original, T[] sorted);
}
