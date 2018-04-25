package org.pg4200.datastructure.map.hash;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class ChainingHashMapTest  extends MyHashMapTestTemplate{

    @Override
    protected <K, V> MyHashMap<K, V> getInstance() {
        return new ChainingHashMap<>();
    }
}