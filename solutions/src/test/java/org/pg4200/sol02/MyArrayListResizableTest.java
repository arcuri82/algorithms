package org.pg4200.sol02;

import org.pg4200.les02.list.MyList;
import org.pg4200.les02.list.MyListTestTemplate;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyArrayListResizableTest extends MyListTestTemplate {

    @Override
    protected <T> MyList<T> getNewInstance(Class<T> klass) {
        return new MyArrayListResizable<>(1);
    }
}