package org.pg4200.datastructure.delete;

import org.pg4200.les02.delete.DeleteContainer;
import org.pg4200.les02.delete.ListDeleteContainer;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class ListDeleteContainerTest extends DeleteContainerTestTemplate{

    @Override
    protected <T> DeleteContainer<T> getNewInstance(Class<T> klass) {
        return new ListDeleteContainer<>();
    }
}