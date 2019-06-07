package org.pg4200.les05;

/**
 * Created by arcuri82 on 22-Aug-17.
 */
public class MyMapBinarySearchArrayTest extends MyMapTestTemplate{

    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getInstance() {
        return new MyMapBinarySearchArray<>();
    }
}