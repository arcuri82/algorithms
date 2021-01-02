package org.pg4200.sol02;

import org.pg4200.les02.list.MyList;
import org.pg4200.les02.list.MyListTestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class MyMiddleBidirectionalLinkedListTest  extends MyListTestTemplate {

    @Override
    protected <T> MyList<T> getNewInstance(Class<T> klass) {
        return new MyMiddleBidirectionalLinkedList<>();
    }
}