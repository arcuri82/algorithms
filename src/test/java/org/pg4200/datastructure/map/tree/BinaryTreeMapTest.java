package org.pg4200.datastructure.map.tree;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 23-Aug-17.
 */
public class BinaryTreeMapTest extends MyTreeBasedMapTestTemplate{

    @Override
    protected <K extends Comparable<K>, V> MyTreeBasedMap<K, V> getTreeInstance() {
        return new BinaryTreeMap<>();
    }
}