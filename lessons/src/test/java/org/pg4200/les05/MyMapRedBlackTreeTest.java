package org.pg4200.les05;

/**
 * Created by arcuri82 on 06-Sep-17.
 */
public class MyMapRedBlackTreeTest extends MyMapTestTemplate {

    protected <K extends Comparable<K>, V> MyMapTreeBased<K, V> getTreeInstance() {
        return new MyMapRedBlackTree<>();
    }

    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getInstance() {
        return getTreeInstance();
    }
}