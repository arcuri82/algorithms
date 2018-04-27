package org.pg4200.les01.array;

import org.pg4200.les01.MyStringContainerWithIndex;
import org.pg4200.les01.MyStringContainerWithIndexTestTemplate;

/**
 * Created by arcuri82 on 14-Aug-17.
 */
public class MyArrayStringContainerTestFailing extends MyStringContainerWithIndexTestTemplate {

    @Override
    protected MyStringContainerWithIndex getNewInstance() {
        return new MyArrayStringContainer(4);
    }

    /*
        When this test suite is run, at least one test fail.
        Why?
        Because we have one test in which we try to add 5 elements, but here we chose that the backing array
        has size 4.

        So, couldn't we just choose a starting size of 1_000_000_000 to solve this problem???
        Of course we could, but then this trivial example would take 1GB of RAM for each single
        container class...
     */
}
