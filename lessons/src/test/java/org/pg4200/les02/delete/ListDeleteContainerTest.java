package org.pg4200.les02.delete;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class ListDeleteContainerTest extends DeleteContainerTestTemplate{

    @Override
    protected <T> DeleteContainer<T> getNewInstance(Class<T> klass) {
        return new ListDeleteContainer<>();
    }
}