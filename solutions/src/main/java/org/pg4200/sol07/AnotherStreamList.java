package org.pg4200.sol07;

import org.pg4200.les07.iterator.IterableLinkedList;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class AnotherStreamList<T>  extends IterableLinkedList<T> {

    public AnotherStream<T> stream() {
        return AnotherStreamSupport.createStream(this);
    }
}
