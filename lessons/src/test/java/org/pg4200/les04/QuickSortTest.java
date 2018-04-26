package org.pg4200.les04;

import org.pg4200.les03.MySort;
import org.pg4200.les03.SortTestTemplate;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class QuickSortTest extends SortTestTemplate {

    @Override
    protected MySort getInstance() {
        return new QuickSort();
    }
}
