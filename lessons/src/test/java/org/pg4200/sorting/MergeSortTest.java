package org.pg4200.sorting;

import org.pg4200.les03.MySort;
import org.pg4200.les04.MergeSort;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class MergeSortTest extends SortTestTemplate{

    @Override
    protected MySort getInstance() {
        return new MergeSort();
    }
}