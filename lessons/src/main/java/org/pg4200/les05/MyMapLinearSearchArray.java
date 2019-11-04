package org.pg4200.les05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by arcuri82 on 21-Aug-17.
 */
public class MyMapLinearSearchArray<K extends Comparable<K>,V> implements MyMap<K,V>{

    private class Entry<K extends Comparable<K>,V> implements Comparable<Entry<K,V>>{
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Entry<K, V> o) {
            //Note that here we ignore the "value"
            return key.compareTo(o.key);
        }
    }

    private List<Entry<K,V>> data = new ArrayList<>();


    @Override
    public void put(K key, V value) {

        Objects.requireNonNull(key);

        Entry entry = new Entry<>(key, value);

        int index = Search.findInNonSorted(data, entry);

        if(index < 0){
            data.add(entry);
        } else {
            data.get(index).value = value;
        }
    }

    @Override
    public void delete(K key) {

        Objects.requireNonNull(key);

        int index = Search.findInNonSorted(data, new Entry<>(key, null));

        if(index >= 0){
            data.remove(index);
        }
    }

    @Override
    public V get(K key) {

        Objects.requireNonNull(key);

        int index = Search.findInNonSorted(data, new Entry<>(key, null));

        if(index < 0){
            return null;
        }

        return data.get(index).value;
    }

    @Override
    public int size() {
        return data.size();
    }
}
