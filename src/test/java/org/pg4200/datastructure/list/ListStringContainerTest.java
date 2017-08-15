package org.pg4200.datastructure.list;

import org.pg4200.datastructure.StringContainerWithIndex;
import org.pg4200.datastructure.StringContainerWithIndexTestTemplate;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class ListStringContainerTest extends StringContainerWithIndexTestTemplate{

    @Override
    protected StringContainerWithIndex getNewInstance() {
        return new ListStringContainer();
    }
}