package org.pg4200.les07.stream;

import org.pg4200.les07.iterator.MyIterableLinkedList;

/**
 * Created by arcuri82 on 03-Oct-17.
 */
public class MyStreamCollectionList<T> extends MyIterableLinkedList<T> implements MyStreamCollection<T> {

    @Override
    public MyStream<T> stream() {
        return MyStreamSupport.createStream(this);
    }
}
