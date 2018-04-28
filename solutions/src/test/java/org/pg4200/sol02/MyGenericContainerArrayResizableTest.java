package org.pg4200.sol02;

import org.pg4200.les02.generic.MyGenericContainer;
import org.pg4200.les02.generic.MyGenericContainerTestTemplate;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyGenericContainerArrayResizableTest extends MyGenericContainerTestTemplate {

    @Override
    protected <T> MyGenericContainer<T> getNewInstance(Class<T> klass) {
        return new MyGenericContainerArrayResizable<>(1);
    }
}