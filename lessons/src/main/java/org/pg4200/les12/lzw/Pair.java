package org.pg4200.les12.lzw;

/**
 * Created by arcuri82 on 11-Jun-19.
 */
public class Pair<K,V> {

    private final K key;
    private final V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
