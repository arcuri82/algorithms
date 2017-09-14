package org.pg4200.datastructure.map.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arcuri82 on 14-Sep-17.
 */
public class ChainingHashMap<K,V> implements MyHashMap<K,V> {

    /**
     * Important this is a prime number.
     * "A prime number (or a prime) is a natural number greater than 1 that has no
     * positive divisors other than 1 and itself"
     * https://en.wikipedia.org/wiki/Prime_number
     */
    private final int M = 997;

    private class Entry{
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private List<Entry>[] data = new ArrayList[M];


    @Override
    public void put(K key, V value) {

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

        //if we arrive here, it means the key is new
        list.add(new Entry(key, value));
    }

    private int index(K key){

        /*
            The hash is an integer, so in the range -2B, +2B
            But here we want to map into 0..M-1, ie a valid
            index on the array.

            So, first step is to make sure the hash is positive,
            by throwing away its first leftmost bit (which define
            the sign of the number).

            How to do it? By using the mask 0x7f_ff_ff_ff
            But why does it mean?

            An F is the value 15 in hexadecimal format, which in
            binary is 1111 (ie, 2^3 + 2^2 + 2^1 + 2^0 = 8 + 4 + 2 + 1)
            So an "& F" means take all the bits at that position.
            However, if we want to skip a first bit in a &, we
            need the following mask in binary

            0111

            as an & with 0 is always 0, ie
            0 & 0 = 0
            0 & 1 = 0
            1 & 0 = 0
            1 & 1 = 1

            in hexadecimal (and decimal as well), the binary 0111 is
            a 7 (ie 2^2 + 2^1 + 2^0 = 4 + 2 + 1)
         */
        int positiveHash = key.hashCode() & 0x7f_ff_ff_ff;

        /*
            The result of %M is a value in 0..M-1
         */

        return positiveHash % M;
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
