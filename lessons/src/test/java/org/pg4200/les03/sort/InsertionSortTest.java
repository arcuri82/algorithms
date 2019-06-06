package org.pg4200.les03.sort;

import org.pg4200.les03.sort.InsertionSort;
import org.pg4200.les03.sort.MySort;
import org.pg4200.les03.sort.SortTestTemplate;

public class InsertionSortTest extends SortTestTemplate {

    @Override
    protected MySort getInstance() {
        return new InsertionSort();
    }
}