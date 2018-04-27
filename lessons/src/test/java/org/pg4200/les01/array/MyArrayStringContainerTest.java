package org.pg4200.les01.array;

import org.pg4200.les01.MyStringContainerWithIndex;
import org.pg4200.les01.MyStringContainerWithIndexTestTemplate;

/**
 * Created by arcuri82 on 14-Aug-17.
 */
public class MyArrayStringContainerTest extends MyStringContainerWithIndexTestTemplate {

    @Override
    protected MyStringContainerWithIndex getNewInstance() {
        return new MyArrayStringContainer(10);
    }
}