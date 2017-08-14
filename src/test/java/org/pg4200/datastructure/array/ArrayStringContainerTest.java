package org.pg4200.datastructure.array;

import org.pg4200.datastructure.StringContainerWithIndex;
import org.pg4200.datastructure.StringContainerWithIndexTestTemplate;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 14-Aug-17.
 */
public class ArrayStringContainerTest extends StringContainerWithIndexTestTemplate{

    @Override
    protected StringContainerWithIndex getNewInstance() {
        return new ArrayStringContainer(10);
    }
}