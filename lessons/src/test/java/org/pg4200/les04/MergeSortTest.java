package org.pg4200.les04;

import org.pg4200.les03.sort.MySort;
import org.pg4200.les03.sort.SortTestTemplate;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class MergeSortTest extends SortTestTemplate {

    @Override
    protected MySort getInstance() {
        return new MergeSort();
    }
}