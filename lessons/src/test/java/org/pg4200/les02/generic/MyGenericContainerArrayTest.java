package org.pg4200.les02.generic;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyGenericContainerArrayTest extends MyGenericContainerTestTemplate {

    @Override
    protected <T> MyGenericContainer<T> getNewInstance(Class<T> klass) {
        return new MyGenericContainerArray<>(10);
    }
}