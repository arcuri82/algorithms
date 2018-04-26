package org.pg4200.les07.stream;

import org.pg4200.les07.iterator.IterableLinkedList;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class StreamList<T> extends IterableLinkedList<T> implements StreamCollection<T> {

    @Override
    public MyStream<T> stream() {
        return MyStreamSupport.createStream(this);
    }
}
