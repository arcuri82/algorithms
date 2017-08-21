package org.pg4200.sorting;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class QuickSortTest extends SortTestTemplate{

    @Override
    protected MySort getInstance() {
        return new QuickSort();
    }
}
