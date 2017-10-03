package org.pg4200.datastructure.stream;

import org.pg4200.datastructure.iterator.IterableHashMap;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class StreamHashMap<K,V> extends IterableHashMap<K,V> implements StreamCollection<V> {

    @Override
    public MyStream<V> stream() {
        return MyStreamSupport.createStream(this);
    }
}
