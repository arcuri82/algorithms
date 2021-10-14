package org.pg4200.les08.stream;

import org.pg4200.les08.iterator.MyIterableHashMap;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class MyStreamCollectionHashMap<K,V> extends MyIterableHashMap<K,V> implements MyStreamCollection<V> {

    @Override
    public MyStream<V> stream() {
        return MyStreamSupport.createStream(this);
    }
}
