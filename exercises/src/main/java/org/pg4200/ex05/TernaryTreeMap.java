package org.pg4200.ex05;

import org.pg4200.les05.MyMapBinarySearchTree;
import org.pg4200.les05.MyMapTreeBased;

import java.util.Objects;

public class TernaryTreeMap<K extends Comparable<K>, V> implements MyMapTreeBased<K, V> {

    protected class TreeNode {
        public V firstValue;
        public V secondValue;

        public K firstKey;
        public K secondKey;
        /**
         * Pointer to the root of the subtree containing the values lower than this.first
         */
        public TreeNode left;

        /**
         * Pointer to the root of the subtree containing the values between this.first and this.second
         */
        public TreeNode middle;

        /**
         * Pointer to the root of the subtree containing the values larger than this.second
         */
        public TreeNode right;
    }

    private TreeNode root;
    private int size;

    @Override
    public void put(K key, V value) {
        Objects.requireNonNull(key);
        root = put(key, value, root);
    }

    private TreeNode put(K key, V value, TreeNode subtree){

        if (subtree == null) {
            TreeNode node = new TreeNode();
            node.firstKey = key;
            node.firstValue = value;
            size++;
            return node;
        }

        int cmp1 = key.compareTo(subtree.firstKey);

        if(cmp1 < 0){
            subtree.left = put(key, value, subtree.left);
        }
        else if(cmp1 == 0){
            subtree.firstValue = value;
        }
        else if(cmp1 > 0){
            //assert cmp1 > 0;

            if (subtree.secondKey == null) {
                size++;
                subtree.secondKey = key;
                subtree.secondValue = value;
            }
            else {

                int cmp2 = key.compareTo(subtree.secondKey);
                if (cmp2 < 0) {
                    subtree.middle = put(key, value, subtree.middle);
                } else if (cmp2 == 0) {
                    subtree.secondValue = value;
                } else if (cmp2 > 0) {
                    subtree.right = put(key, value, subtree.right);
                }
            }
        }

        return subtree;
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key);
        root = delete(key, root);
    }

    private TreeNode delete(K key, TreeNode subtree){

        if (subtree == null) {
            /*
                This will happen when the key is not found, and we try a
                recursion on a null node.
                In this case, the new root of a null substree is still a null
                subtree, and we can return itself directly (ie null)
             */
            return null;
        }

        int cmp1 = key.compareTo(subtree.firstKey);
        //

        if (cmp1 < 0) {
            subtree.left = delete(key, subtree.left);
            return subtree;
        }
        if(cmp1 == 0){
            size--;
            if(subtree.secondKey == null){
                assert subtree.right == null;

                if(subtree.left == null && subtree.middle == null){
                    return null;
                }

                if(subtree.left == null){
                    return subtree.middle;
                }else if(subtree.middle == null){
                    return subtree.left;
                } else {
                    TreeNode min = min(subtree.middle);
                    subtree.firstKey = min.firstKey;
                    subtree.firstValue = min.firstValue;
                    subtree.middle = deleteMin(subtree.middle);
                    return subtree;
                }
            }
            else{
                if(subtree.middle == null){
                    moveSecondToFirst(subtree);
                    subtree.middle = subtree.right;
                    subtree.right = null;
                    return subtree;
                }
                else if(subtree.left == null){
                    moveSecondToFirst(subtree);
                    subtree.left = subtree.middle;
                    subtree.middle = subtree.right;
                    subtree.right = null;
                    return subtree;
                }
                else{
                    TreeNode min = min(subtree.middle);
                    subtree.firstKey = min.firstKey;
                    subtree.firstValue = min.firstValue;
                    subtree.middle = deleteMin(subtree.middle);

                    return subtree;

                }
            }

        }

        if(cmp1 > 0){
            if(subtree.secondKey == null){
                subtree.middle = delete(key, subtree.middle);
                return subtree;
            }

            int cmp2 = key.compareTo(subtree.secondKey);

            if(cmp2 < 0){
                subtree.middle = delete(key, subtree.middle);
                return subtree;
            }

            if(cmp2 > 0){
                subtree.right = delete(key, subtree.right);
                return subtree;
            }

            assert cmp2 == 0;

            size--;

            if(subtree.right == null){
                subtree.secondKey =null;
                subtree.secondValue =null;

                return subtree;
            }
            else{
                TreeNode min = min(subtree.right);
                subtree.secondKey = min.firstKey;
                subtree.secondValue = min.firstValue;
                subtree.right = deleteMin(subtree.right);
                return subtree;
            }

        }

        return subtree;

    }

    private void moveSecondToFirst(TreeNode node){
        node.firstKey = node.secondKey;
        node.firstValue = node.secondValue;

        node.secondKey = null;
        node.secondValue = null;
    }

    private TreeNode min(TreeNode subtreeRoot) {
        if (subtreeRoot.left == null) {
            return subtreeRoot;
        }
        return min(subtreeRoot.left);
    }

    private TreeNode deleteMin(TreeNode subtreeRoot) {

        if (subtreeRoot.left == null) {
            if (subtreeRoot.secondKey == null) {
                return subtreeRoot.middle;
            } else {
                moveSecondToFirst(subtreeRoot);
                subtreeRoot.left = subtreeRoot.middle;
                subtreeRoot.middle = subtreeRoot.right;
                subtreeRoot.right = null;
                return subtreeRoot;
            }
        }

        subtreeRoot.left = deleteMin(subtreeRoot.left);

        return subtreeRoot;
    }

    private TreeNode max(TreeNode subtreeRoot) {
        if (subtreeRoot.right == null) {
            return subtreeRoot;
        }
        return max(subtreeRoot.right);
    }

    private TreeNode deleteMax(TreeNode subtreeRoot) {

        if (subtreeRoot.right == null) {
            return subtreeRoot.left;
        }

        subtreeRoot.right = deleteMax(subtreeRoot.right);

        return subtreeRoot;
    }

    @Override
    public V get(K key) {
        Objects.requireNonNull(key);
        return get(key, root);
    }

    private V get(K key, TreeNode subtree){
        if (subtree == null) {
            return null;
        }

        int cmp1 = key.compareTo(subtree.firstKey);
        if(cmp1 < 0){
            return get(key, subtree.left);
        }
        else if (cmp1 == 0) {
            return subtree.firstValue;
        }
        else if (cmp1 > 0){
            if(subtree.secondValue == null){
                return null;
            }

            assert subtree.firstKey != null;
            assert subtree.secondKey != null;

            int cmp2 = key.compareTo(subtree.secondKey);
            if (cmp2 == 0) {
                return subtree.secondValue;
            }


            else if(cmp2 > 0){
                return get(key, subtree.right);
            }
            else{
                return get(key, subtree.middle);
            }
        }

        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getMaxTreeDepth() {
        return 0;
    }
}
