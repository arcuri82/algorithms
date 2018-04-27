package org.pg4200.les07.set;

import org.pg4200.les06.MyHashMapWithLists;
import org.pg4200.les06.MyHashMap;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class MySetHashMap<E> implements MySet<E> {

    /*
        We use a map to represent a set.
     */
    private MyHashMap<E, Object> map = new MyHashMapWithLists<>();

    /**
     * We do not care about the values in the map.
     * So, let's create a single object, and re-use it for
     * all insertions.
     */
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
