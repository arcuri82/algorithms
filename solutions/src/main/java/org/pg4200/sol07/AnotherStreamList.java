package org.pg4200.sol07;

import org.pg4200.ex07.AnotherStream;
import org.pg4200.les07.iterator.MyIterableLinkedList;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class AnotherStreamList<T>  extends MyIterableLinkedList<T> {

    public AnotherStream<T> stream() {
        return AnotherStreamSupport.createStream(this);
    }
}
