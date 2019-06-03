package org.pg4200.les01.arraylist;

import org.pg4200.les01.MyListString;
import org.pg4200.les01.MyListStringTestTemplate;

/**
 * Created by arcuri82 on 14-Aug-17.
 */
public class MyArrayListStringTest extends MyListStringTestTemplate {

    @Override
    protected MyListString getNewInstance() {
        return new MyArrayListString(10);
    }
}