package org.pg4200.sol06;

import org.pg4200.les06.hash.MyHashMap;
import org.pg4200.les06.hash.MyHashMapTestTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 02-May-18.
 */
public class HashMapLinearProbeTest extends MyHashMapTestTemplate {

    @Override
    protected <K, V> MyHashMap<K, V> getInstance() {
        return new HashMapLinearProbe<>();
    }
}