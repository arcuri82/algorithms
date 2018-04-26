package org.pg4200.les01.list;

import org.pg4200.les01.StringContainerWithIndex;
import org.pg4200.les01.StringContainerWithIndexTestTemplate;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class DelegateListContainerTest extends StringContainerWithIndexTestTemplate{

    @Override
    protected StringContainerWithIndex getNewInstance() {
        return new DelegateListContainer();
    }
}