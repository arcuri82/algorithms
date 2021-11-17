package org.pg4200.sol08;

import org.pg4200.ex08.ExtendedList;
import org.pg4200.ex08.ExtendedListTestTemplate;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class ExtendedListImplTest extends ExtendedListTestTemplate {

    @Override
    protected <T> ExtendedList<T> getInstance(Class<T> klass) {
        return new ExtendedListImpl<>();
    }
}