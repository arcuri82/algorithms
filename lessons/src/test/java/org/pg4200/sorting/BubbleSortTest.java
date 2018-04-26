package org.pg4200.sorting;

import org.pg4200.les03.BubbleSort;
import org.pg4200.les03.MySort;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class BubbleSortTest extends SortTestTemplate{

    @Override
    protected MySort getInstance() {
        return new BubbleSort();
    }
}