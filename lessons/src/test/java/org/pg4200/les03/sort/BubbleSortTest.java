package org.pg4200.les03.sort;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class BubbleSortTest extends SortTestTemplate{

    @Override
    protected MySort getInstance() {
        return new BubbleSort();
    }
}