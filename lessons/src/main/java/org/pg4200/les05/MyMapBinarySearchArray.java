package org.pg4200.les05;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by arcuri82 on 22-Aug-17.
 */
public class MyMapBinarySearchArray<K extends Comparable<K>,V> implements MyMap<K,V> {


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


    /**
     * Here, we need to make sure that every time we add/delete from this
     * container then the backing data list is kept ordered
     */
    private List<Entry<K,V>> sortedData = new ArrayList<>();


    @Override
    public void put(K key, V value) {

        Objects.requireNonNull(key);

        Entry entry = new Entry<>(key, value);

        /*
            This is going to be O(log n) instead of O(n)
         */
        int index = Search.findInSorted(sortedData, entry);

        if(index < 0){
            //we do not add at the end. but we choose a position
            //in which the list will be kept sorted after the insertion
            for(int i=0; i<sortedData.size(); i++){
                if(sortedData.get(i).key.compareTo(key) > 0){
                    /*
                        this means that the current element
                        at position i is greater than new key.
                        So, to keep the list sorted, we need to insert
                        before it
                     */

                    /*
                        this shifts all elements from i on to the right.
                     */
                    sortedData.add(i, entry);

                    return;
                }
            }
            /*
                if we arrive here, then the for loop completed with no break.
                so add at the end.
             */
            sortedData.add(entry);

        } else {

            assert index >= 0;
            sortedData.get(index).value = value;
        }

        /*
            Even if the search for index is O(log n), because inserts still
            need to possible shift all elements, complexity is still O(n)
         */
    }

    @Override
    public void delete(K key) {

        Objects.requireNonNull(key);

        int index = Search.findInSorted(sortedData, new Entry<>(key, null));

        if(index >= 0){
            /*
                Left shifting is still going to keep the list sorted
             */
            sortedData.remove(index);
        }

        /*
            Although the search by index is O(log n), the removing operation
            is still O(n), as all elements could be shifted (eg if remove element
            at position 0).

            Although this is faster than previous linear version, asymptotically it
            is still the same.
         */
    }

    @Override
    public V get(K key) {

        Objects.requireNonNull(key);

        //O(log n) complexity
        int index = Search.findInSorted(sortedData, new Entry<>(key, null));

        if(index < 0){
            return null;
        }

        return sortedData.get(index).value;
    }

    @Override
    public int size() {
        return sortedData.size();
    }
}
