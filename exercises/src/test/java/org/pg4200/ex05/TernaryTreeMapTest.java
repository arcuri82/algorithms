package org.pg4200.ex05;

import org.pg4200.les05.MyMap;
import org.pg4200.les05.MyMapTestTemplate;

public class TernaryTreeMapTest extends MyMapTestTemplate {
    @Override
    protected <K extends Comparable<K>, V> MyMap<K, V> getInstance() {
        return new TernaryTreeMap();
    }
}
