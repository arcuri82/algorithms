package org.pg4200.les01.list;

import org.pg4200.les01.MyStringContainerWithIndex;
import org.pg4200.les01.MyStringContainerWithIndexTestTemplate;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyListStringContainerTest extends MyStringContainerWithIndexTestTemplate {

    @Override
    protected MyStringContainerWithIndex getNewInstance() {
        return new MyListStringContainer();
    }
}