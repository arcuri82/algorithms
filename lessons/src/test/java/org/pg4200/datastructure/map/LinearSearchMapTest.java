package org.pg4200.datastructure.map;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 22-Aug-17.
 */
public class LinearSearchMapTest extends MyMapTestTemplate{

    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getInstance() {
        return new LinearSearchMap<>();
    }
}