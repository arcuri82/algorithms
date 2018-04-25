package org.pg4200.datastructure.map.tree;

import org.pg4200.datastructure.map.MyMap;
import org.pg4200.datastructure.map.MyMapTestTemplate;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 06-Sep-17.
 */
public class RedBlackTreeMapTest extends MyMapTestTemplate {

    protected <K extends Comparable<K>, V> MyTreeBasedMap<K, V> getTreeInstance() {
        return new RedBlackTreeMap<>();
    }

    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getInstance() {
        return getTreeInstance();
    }
}