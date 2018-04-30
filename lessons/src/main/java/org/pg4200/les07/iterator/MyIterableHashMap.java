package org.pg4200.les07.iterator;

import org.pg4200.les06.hash.MyHashMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by arcuri82 on 15-Sep-17.
 */
public class MyIterableHashMap<K,V> implements MyHashMap<K,V>, Iterable<V> {

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

    public MyIterableHashMap(){
        this(997);
    }

    public MyIterableHashMap(int capacity){
        data = new ArrayList[capacity];
        modificationCounter = 0;
    }


    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            private int initialCounter;
            private int arrayIndex;
            private int listIndex;

            {
                //initialization code, like in a constructor

                initialCounter = modificationCounter;

                arrayIndex = -1;
                listIndex = -1;

                for(int i=0; i < data.length; i++){
                    List<Entry> list = data[i];
                    if(list != null && !list.isEmpty()){
                        arrayIndex = i;
                        listIndex = 0;
                        break;
                    }
                }
            }

            @Override
            public boolean hasNext() {
                checkModifications();
                return arrayIndex >= 0;
            }

            @Override
            public V next() {
                checkModifications();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                List<Entry> current = data[arrayIndex];

                V value = current.get(listIndex).value;

                if(listIndex < current.size() - 1){
                    //more data in current list
                    listIndex++;
                } else {

                    boolean found = false;

                    for(int i=arrayIndex+1; i < data.length; i++){
                        List<Entry> list = data[i];
                        if(list != null && !list.isEmpty()){
                            arrayIndex = i;
                            listIndex = 0;
                            found = true;
                            break;
                        }
                    }

                    if(! found){
                        arrayIndex = -1;
                    }
                }

                return value;
            }

            private void checkModifications() {
                if (initialCounter != modificationCounter) {
                    throw new IllegalStateException();
                }
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
