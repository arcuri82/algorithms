package org.pg4200.les02.list;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyArrayListTest extends MyListTestTemplate {

    @Override
    protected <T> MyList<T> getNewInstance(Class<T> klass) {
        return new MyArrayList<>(10);
    }
}