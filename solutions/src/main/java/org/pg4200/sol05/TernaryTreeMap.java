package org.pg4200.sol05;

import org.pg4200.les05.MyMapTreeBased;

import java.util.Objects;

/**
 * Created by arcuri82 on 23-Aug-17.
 */
public class TernaryTreeMap<K extends Comparable<K>, V> implements MyMapTreeBased<K, V> {


    private class TreeNode {
        private TreeNode left;
        private TreeNode middle;
        private TreeNode right;

        private K firstKey;
        private K secondKey;

        private V firstValue;
        private V secondValue;
    }

    protected TreeNode root;

    protected int size;

    @Override
    public void put(K key, V value) {
        Objects.requireNonNull(key);
        root = put(key, value, root);
    }

    private TreeNode put(K key, V value, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            TreeNode node = new TreeNode();
            node.firstKey = key;
            node.firstValue = value;
            size++;
            return node;
        }

        int fc = key.compareTo(subtreeRoot.firstKey);

        if (fc < 0) {
            subtreeRoot.left = put(key, value, subtreeRoot.left);
        } else if (fc == 0) {
            subtreeRoot.firstValue = value;
        } else if (fc > 0) {

            if (subtreeRoot.secondKey == null) {
                size++;
                subtreeRoot.secondKey = key;
                subtreeRoot.secondValue = value;
            } else {

                int sc = key.compareTo(subtreeRoot.secondKey);
                if (sc < 0) {
                    subtreeRoot.middle = put(key, value, subtreeRoot.middle);
                } else if (sc == 0) {
                    subtreeRoot.secondValue = value;
                } else {
                    subtreeRoot.right = put(key, value, subtreeRoot.right);
                }
            }
        }

        return subtreeRoot;
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key);
        root = delete(key, root);
    }

    private TreeNode delete(K key, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            return null;
        }

        int fc = key.compareTo(subtreeRoot.firstKey);

        if (fc < 0) {
            subtreeRoot.left = delete(key, subtreeRoot.left);
            return subtreeRoot;
        }

        if (fc == 0) {

            size--;

            if (subtreeRoot.secondKey == null) {

                assert subtreeRoot.right == null;

                if (subtreeRoot.left == null && subtreeRoot.middle == null) {
                    return null;
                }

                if (subtreeRoot.left == null) {
                    return subtreeRoot.middle;
                } else if(subtreeRoot.middle == null){
                    return subtreeRoot.left;
                } else {
                    TreeNode min = min(subtreeRoot.middle);
                    subtreeRoot.firstKey = min.firstKey;
                    subtreeRoot.firstValue = min.firstValue;
                    subtreeRoot.middle = deleteMin(subtreeRoot.middle);
                    return subtreeRoot;
                }
            } else {

                if(subtreeRoot.middle == null){
                    moveSecondToFirst(subtreeRoot);
                    subtreeRoot.middle = subtreeRoot.right;
                    subtreeRoot.right = null;
                    return subtreeRoot;
                } else if(subtreeRoot.left == null){
                    moveSecondToFirst(subtreeRoot);
                    subtreeRoot.left = subtreeRoot.middle;
                    subtreeRoot.middle = subtreeRoot.right;
                    subtreeRoot.right = null;
                    return subtreeRoot;
                } else {
                    TreeNode min = min(subtreeRoot.middle);
                    subtreeRoot.firstKey = min.firstKey;
                    subtreeRoot.firstValue = min.firstValue;
                    subtreeRoot.middle = deleteMin(subtreeRoot.middle);
                    return subtreeRoot;
                }
            }
        }


        if (fc > 0) {

            if(subtreeRoot.secondKey == null){
                subtreeRoot.middle = delete(key, subtreeRoot.middle);
                return subtreeRoot;
            }

            int sc = key.compareTo(subtreeRoot.secondKey);

            if(sc < 0){
                subtreeRoot.middle = delete(key, subtreeRoot.middle);
                return subtreeRoot;
            }

            if(sc > 0){
                subtreeRoot.right = delete(key, subtreeRoot.right);
                return subtreeRoot;
            }

            assert sc == 0;

            size--;

            if(subtreeRoot.right == null){
                subtreeRoot.secondKey = null;
                subtreeRoot.secondValue = null;
                return subtreeRoot;
            } else {
                TreeNode min = min(subtreeRoot.right);
                subtreeRoot.secondKey = min.firstKey;
                subtreeRoot.secondValue = min.firstValue;
                subtreeRoot.right = deleteMin(subtreeRoot.right);
                return subtreeRoot;
            }
        }

        return subtreeRoot;
    }

    private void moveSecondToFirst(TreeNode subtreeRoot) {
        subtreeRoot.firstKey = subtreeRoot.secondKey;
        subtreeRoot.firstValue = subtreeRoot.secondValue;
        subtreeRoot.secondKey = null;
        subtreeRoot.secondValue = null;
    }

    private TreeNode min(TreeNode subtreeRoot) {
        if (subtreeRoot.left == null) {
            return subtreeRoot;
        }
        return min(subtreeRoot.left);
    }

    private TreeNode deleteMin(TreeNode subtreeRoot) {

        if (subtreeRoot.left == null) {
            if(subtreeRoot.secondKey == null) {
                return subtreeRoot.middle; //might be null
            }  else {
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

    @Override
    public V get(K key) {
        Objects.requireNonNull(key);
        return get(key, root);
    }

    private V get(K key, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            return null;
        }

        int fc = key.compareTo(subtreeRoot.firstKey);

        if (fc < 0) {
            return get(key, subtreeRoot.left);
        } else if (fc == 0) {
            return subtreeRoot.firstValue;
        } else if (fc > 0) {

            if (subtreeRoot.secondKey == null) {
                return get(key, subtreeRoot.middle);
            } else {

                int sc = key.compareTo(subtreeRoot.secondKey);
                if (sc < 0) {
                    return get(key, subtreeRoot.middle);
                } else if (sc == 0) {
                    return subtreeRoot.secondValue;
                } else {
                    return get(key, subtreeRoot.right);
                }
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

        if (root == null) {
            return 0;
        }

        return depth(root);
    }

    protected int depth(TreeNode node) {

        int leftDepth = 0;
        int middleDepth = 0;
        int rightDepth = 0;

        if (node.left != null) {
            leftDepth = depth(node.left);
        }
        if (node.middle != null) {
            middleDepth = depth(node.middle);
        }
        if (node.right != null) {
            rightDepth = depth(node.right);
        }

        return 1 + Math.max(leftDepth, Math.max(middleDepth, rightDepth));
    }
}
