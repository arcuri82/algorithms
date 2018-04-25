package org.pg4200.datastructure.delete;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class ListDeleteContainerTest extends DeleteContainerTestTemplate{

    @Override
    protected <T> DeleteContainer<T> getNewInstance(Class<T> klass) {
        return new ListDeleteContainer<>();
    }
}