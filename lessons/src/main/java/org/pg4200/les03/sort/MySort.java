package org.pg4200.les03.sort;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public interface MySort {

    /**
     *  Sort the input array in ascending order.
     *  The array can be of any type, as long as such
     *  type implements the interface java.land.Comparable
     *
     *  Note that Comparable use Generics, as it defines a method
     *  (called compareTo) used to compare with other instances.
     *  If you need to compare an object of (generic) type T with
     *  another instance of same type T, then you need Comparable < T >.
     *  So you want a class of type T that does implement the interface Comparable < T >.
     */
    <T extends Comparable<T>> void sort(T[] array);
}
