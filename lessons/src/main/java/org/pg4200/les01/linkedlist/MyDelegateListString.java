package org.pg4200.les01.linkedlist;

import org.pg4200.les01.MyListString;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by arcuri82 on 15-Aug-17.
 */
public class MyDelegateListString implements MyListString {

    /*
        The Java API provides many implementations for containers, eg in
        the "java.util.*" package.
        It is unlikely that you will ever need to implement your own containers,
        as we do here in this course.
        The point is that, to properly choose which container to use, you need
        to understand how they work internally.

        Technically, to fulfil the contract of the StringContainerWithIndex interface,
        I could use internally an actual list from the Java API, and "delegate" all the
        methods on such list.

        It is technically correct, although quite pointless: why not using the Java API directly?
        In the exercises in this course (and the exam), you are NOT allowed to write this kind
        of delegate code, unless otherwise specified.
        Even worse, do NOT do it in a job interview...
        Note: we will looking into "Generics" (eg, "<String>") next class.
     */
    private List<String> delegate = new LinkedList<>();

    @Override
    public String get(int index) {

        try {
            return delegate.get(index);
        } catch (Exception e){
            /*
                The Java API has different semantics regarding how to
                handle invalid inputs, ie it throws an exception
             */
            return null;
        }
    }

    @Override
    public void add(String value) {
        delegate.add(value);
    }

    @Override
    public int size() {
        return delegate.size();
    }
}
