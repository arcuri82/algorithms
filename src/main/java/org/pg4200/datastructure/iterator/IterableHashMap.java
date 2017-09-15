package org.pg4200.datastructure.iterator;

import org.pg4200.datastructure.map.hash.MyHashMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by arcuri82 on 15-Sep-17.
 */
public class IterableHashMap<K,V> implements MyHashMap<K,V>, Iterable<V> {

    private class Entry{
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Entry>[] data;

    private int modificationCounter;

    public IterableHashMap(){
        this(997);
    }

    public IterableHashMap(int capacity){
        data = new ArrayList[capacity];
        modificationCounter = 0;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            private int initialCounter = modificationCounter;

            private int i = -1;
            private int j = -1;

            @Override
            public boolean hasNext() {
                return false; //TODO
            }

            @Override
            public V next() {
                return null; //TODO
            }
        };
    }


    @Override
    public void put(K key, V value) {

        modificationCounter++;

        int i = index(key);

        if(data[i] == null){
            data[i] = new ArrayList<>();
        }

        List<Entry> list = data[i];

        for(int j=0; j<list.size(); j++){
            Entry entry = list.get(j);
            if(key.equals(entry.key)){
                entry.value = value;
                return;
            }
        }

        list.add(new Entry(key, value));
    }

    private int index(K key){

        int positiveHash = key.hashCode() & 0x7f_ff_ff_ff;

        return positiveHash % data.length;
    }

    @Override
    public void delete(K key) {

        int i = index(key);

        if(data[i] == null){
            return;
        }

        List<Entry> list = data[i];

        for(int j=0; j<list.size(); j++){
            Entry entry = list.get(j);
            if(key.equals(entry.key)){
                list.remove(j);
                modificationCounter++;
                return;
            }
        }
    }

    @Override
    public V get(K key) {
        int i = index(key);

        if(data[i] == null){
            return null;
        }

        List<Entry> list = data[i];

        for(int j=0; j<list.size(); j++){
            Entry entry = list.get(j);
            if(key.equals(entry.key)){
                return entry.value;
            }
        }

        return null;
    }

    @Override
    public int size() {

        int size = 0;
        for(int i=0; i<data.length; i++){
            if(data[i] != null){
                size += data[i].size();
            }
        }

        return size;
    }
}
