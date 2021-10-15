package org.pg4200.ex08;

import org.pg4200.les08.iterator.MyIterableLinkedList;

public class AnotherExampleStreamList<T> extends MyIterableLinkedList<T> {

    public AnotherStream<T> stream(){
        return AnotherExampleStreamSupport.createStream(this);
    }
}
