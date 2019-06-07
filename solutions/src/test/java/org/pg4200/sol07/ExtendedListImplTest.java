package org.pg4200.sol07;

import org.pg4200.ex07.ExtendedList;
import org.pg4200.ex07.ExtendedListTestTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 07-Jun-19.
 */
public class ExtendedListImplTest extends ExtendedListTestTemplate {

    @Override
    protected <T> ExtendedList<T> getInstance(Class<T> klass) {
        return new ExtendedListImpl<>();
    }
}