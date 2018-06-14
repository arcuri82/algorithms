package org.pg4200.les05;

/**
 * We are going to show a few implementations of maps using trees.
 * An important aspect of a tree that does impact its performance is
 * its maximum depth.
 *
 * As this info has no meaning for the other kinds of maps, here we
 * create a sub-interface.
 *
 * Created by arcuri82 on 22-Aug-17.
 */
public interface MyMapTreeBased<K extends Comparable<K>, V> extends MyMap<K,V> {

    int getMaxTreeDepth();

}
