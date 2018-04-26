package org.pg4200.datastructure.delete;

import org.pg4200.les02.delete.ArrayDeleteContainer;
import org.pg4200.les02.delete.DeleteContainer;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class ArrayDeleteContainerTest extends DeleteContainerTestTemplate{

    @Override
    protected <T> DeleteContainer<T> getNewInstance(Class<T> klass) {
        return new ArrayDeleteContainer<>(10);
    }
}