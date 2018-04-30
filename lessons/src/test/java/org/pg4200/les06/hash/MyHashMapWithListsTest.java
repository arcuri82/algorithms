package org.pg4200.les06.hash;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class MyHashMapWithListsTest extends MyHashMapTestTemplate{

    @Override
    protected <K, V> MyHashMap<K, V> getInstance() {
        return new MyHashMapWithLists<>();
    }
}