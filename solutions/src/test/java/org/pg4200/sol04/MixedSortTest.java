package org.pg4200.sol04;

import org.pg4200.les03.sort.MySort;
import org.pg4200.les03.sort.SortTestTemplate;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class MixedSortTest extends SortTestTemplate {

    @Override
    protected MySort getInstance() {
        return new MixedSort(4);
    }
}
