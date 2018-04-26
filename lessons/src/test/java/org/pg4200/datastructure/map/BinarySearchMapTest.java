package org.pg4200.datastructure.map;

import org.pg4200.les05.BinarySearchMap;
import org.pg4200.les05.MyMap;

/**
 * Created by arcuri82 on 22-Aug-17.
 */
public class BinarySearchMapTest extends MyMapTestTemplate{

    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getInstance() {
        return new BinarySearchMap<>();
    }
}