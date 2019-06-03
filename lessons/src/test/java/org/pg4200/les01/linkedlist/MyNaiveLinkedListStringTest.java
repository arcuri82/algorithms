package org.pg4200.les01.linkedlist;

import org.pg4200.les01.MyListString;
import org.pg4200.les01.MyListStringTestTemplate;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyNaiveLinkedListStringTest extends MyListStringTestTemplate {

    @Override
    protected MyListString getNewInstance() {
        return new MyNaiveLinkedListString();
    }
}