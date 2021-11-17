package org.pg4200.sol08;

import org.pg4200.ex08.AnotherStream;
import org.pg4200.les08.iterator.MyIterableLinkedList;

/**
 * Created by arcuri82 on 04-Oct-17.
 */
public class AnotherStreamList<T>  extends MyIterableLinkedList<T> {

    public AnotherStream<T> stream() {
        return AnotherStreamSupport.createStream(this);
    }
}
