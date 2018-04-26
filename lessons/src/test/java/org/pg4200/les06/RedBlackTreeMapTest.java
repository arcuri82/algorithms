package org.pg4200.les06;

import org.pg4200.les05.MyMap;
import org.pg4200.les05.MyMapTestTemplate;
import org.pg4200.les05.MyTreeBasedMap;

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