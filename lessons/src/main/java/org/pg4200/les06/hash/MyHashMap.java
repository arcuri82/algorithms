package org.pg4200.les06.hash;

/**
 * Note: this interface does NOT extend MyMap.
 * Why?
 * Because for hash maps there is no constrains that
 * the keys must be sortable.
 * Here we just need to have an hashcode, and all
 * objects in Java have the method "hashCode()", as
 * inherited from java.lang.Object
 *
 * Created by arcuri82 on 14-Sep-17.
 */
public interface MyHashMap<K, V> {

    void put(K key, V value);

    void delete(K key);

    V get(K key);

    int size();

    default boolean isEmpty(){
        return size() == 0;
    }
}
