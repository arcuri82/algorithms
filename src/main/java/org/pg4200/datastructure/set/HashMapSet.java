package org.pg4200.datastructure.set;

import org.pg4200.datastructure.map.hash.ChainingHashMap;
import org.pg4200.datastructure.map.hash.MyHashMap;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class HashMapSet<E> implements MySet<E> {

    /*
        We use a map to represent a set.
     */
    private MyHashMap<E, Object> map = new ChainingHashMap<>();

    private static final Object PRESENCE = new Object();

    @Override
    public void add(E element) {
        map.put(element, PRESENCE);
    }

    @Override
    public void remove(E element) {
        map.delete(element);
    }

    @Override
    public boolean isPresent(E element) {
        return map.get(element) != null;
    }

    @Override
    public int size() {
        return map.size();
    }
}
