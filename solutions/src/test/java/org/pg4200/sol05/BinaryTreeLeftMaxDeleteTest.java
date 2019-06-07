package org.pg4200.sol05;

import org.pg4200.les05.*;

/**
 * Created by arcuri82 on 30-Apr-18.
 */
public class BinaryTreeLeftMaxDeleteTest extends MyMapBinarySearchTreeTest {

    @Override
    protected <K extends Comparable<K>, V> MyMapTreeBased<K, V> getTreeInstance() {
        return new BinaryTreeLeftMaxDelete<>();
    }

}