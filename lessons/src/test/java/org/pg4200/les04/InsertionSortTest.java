package org.pg4200.les04;

import org.pg4200.les03.MySort;
import org.pg4200.les03.SortTestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest extends SortTestTemplate {

    @Override
    protected MySort getInstance() {
        return new InsertionSort();
    }
}