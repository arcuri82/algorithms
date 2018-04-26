package org.pg4200.sol01;

import org.pg4200.les02.delete.DeleteContainer;
import org.pg4200.les02.delete.DeleteContainerTestTemplate;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyArrayListTest extends DeleteContainerTestTemplate{

    @Override
    protected <T> DeleteContainer<T> getNewInstance(Class<T> klass) {
        return new MyArrayList<>(1);
    }
}