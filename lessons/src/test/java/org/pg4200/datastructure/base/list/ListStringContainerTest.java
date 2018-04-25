package org.pg4200.datastructure.base.list;

import org.pg4200.datastructure.base.StringContainerWithIndex;
import org.pg4200.datastructure.base.StringContainerWithIndexTestTemplate;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class ListStringContainerTest extends StringContainerWithIndexTestTemplate{

    @Override
    protected StringContainerWithIndex getNewInstance() {
        return new ListStringContainer();
    }
}