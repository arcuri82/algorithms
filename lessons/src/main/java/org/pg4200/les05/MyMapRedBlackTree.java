package org.pg4200.les05;

import java.util.Objects;

/**
 * Created by arcuri82 on 24-Aug-17.
 */
public class MyMapRedBlackTree<K extends Comparable<K>, V> implements MyMapTreeBased<K, V> {

    protected class TreeNode {
        public K key;
        public V value;
        public TreeNode left;
        public TreeNode right;
        public boolean is_red; //false means 'black'
    }

    protected TreeNode root;

    private int size;

    @Override
    public void put(K key, V value) {

        Objects.requireNonNull(key);

        root = put(key, value, root);
        root.is_red = false;
    }

    /**
     * Every time we insert an element in a subtree, due
     * to rotations the root of such subtree might change.
     * So we return it, so that the parent can update its
     * link to this subtree
     */
    private TreeNode put(K key, V value, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            TreeNode node = new TreeNode();
            node.key = key;
            node.value = value;
            node.is_red = true;
            size++;
            return node;
        }

        int cmp = key.compareTo(subtreeRoot.key);

        if (cmp < 0) {
            subtreeRoot.left = put(key, value, subtreeRoot.left);
        } else if (cmp > 0) {
            subtreeRoot.right = put(key, value, subtreeRoot.right);
        } else {
            //update already existing key
            subtreeRoot.value = value;
        }

        if (isRed(subtreeRoot.right) && !isRed(subtreeRoot.left)) {
            subtreeRoot = rotateLeft(subtreeRoot);
        }
        if (isRed(subtreeRoot.left) && isRed(subtreeRoot.left.left)) {
            subtreeRoot = rotateRight(subtreeRoot);
        }
        if (isRed(subtreeRoot.left) && isRed(subtreeRoot.right)) {
            flipColors(subtreeRoot);
        }

        return subtreeRoot;
    }

    private boolean isRed(TreeNode node) {
        if (node == null) {
            return false;
        }
        return node.is_red;
    }

    private void flipColors(TreeNode subtreeRoot) {

        subtreeRoot.is_red = !subtreeRoot.is_red;
        if (subtreeRoot.left != null) {
            subtreeRoot.left.is_red = ! subtreeRoot.left.is_red;
        }
        if (subtreeRoot.right != null) {
            subtreeRoot.right.is_red = ! subtreeRoot.right.is_red;
        }
    }

    private TreeNode rotateLeft(TreeNode subtreeRoot) {

        TreeNode x = subtreeRoot.right;
        subtreeRoot.right = x.left;
        x.left = subtreeRoot;
        x.is_red = subtreeRoot.is_red;
        subtreeRoot.is_red = true;
        return x;
    }

    private TreeNode rotateRight(TreeNode subtreeRoot) {

        TreeNode x = subtreeRoot.left;
        subtreeRoot.left = x.right;
        x.right = subtreeRoot;
        x.is_red = subtreeRoot.is_red;
        subtreeRoot.is_red = true;
        return x;
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

        int cmp = key.compareTo(subtreeRoot.key);

        if (cmp == 0) {
            return subtreeRoot.value;
        } else if (cmp > 0) {
            //look at greater values in the right subtree
            return get(key, subtreeRoot.right);
        } else if (cmp < 0) {
            //look at smaller values in the left subtree
            return get(key, subtreeRoot.left);
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
        int rightDepth = 0;

        if (node.left != null) {
            leftDepth = depth(node.left);
        }
        if (node.right != null) {
            rightDepth = depth(node.right);
        }

        return 1 + Math.max(leftDepth, rightDepth);
    }


    //-----------------------------------------------------------
    // Following is not part of the course.
    // It is implemented here just for sake of completeness.


    @Override
    public void delete(K key) {

        Objects.requireNonNull(key);

        if (!isRed(root.left) && !isRed(root.right)) {
            root.is_red = true;
        }

        root = delete(root, key);

        if (!isEmpty()) {
            root.is_red = false;
        }
    }


    private TreeNode delete(TreeNode subtreeRoot, K key) {

        if (key.compareTo(subtreeRoot.key) < 0) {
            if (!isRed(subtreeRoot.left) && !isRed(subtreeRoot.left.left)) {
                subtreeRoot = moveRedLeft(subtreeRoot);
            }
            subtreeRoot.left = delete(subtreeRoot.left, key);
        } else {

            if (isRed(subtreeRoot.left)) {
                subtreeRoot = rotateRight(subtreeRoot);
            }

            if (key.compareTo(subtreeRoot.key) == 0 && (subtreeRoot.right == null)) {
                size--;
                return null;
            }

            if (!isRed(subtreeRoot.right) && !isRed(subtreeRoot.right.left)) {
                subtreeRoot = moveRedRight(subtreeRoot);
            }

            if (key.compareTo(subtreeRoot.key) == 0) {
                TreeNode x = min(subtreeRoot.right);
                subtreeRoot.key = x.key;
                subtreeRoot.value = x.value;
                subtreeRoot.right = deleteMin(subtreeRoot.right);
            } else {
                subtreeRoot.right = delete(subtreeRoot.right, key);
            }
        }
        return balance(subtreeRoot);
    }

    private TreeNode min(TreeNode subtreeRoot) {

        if (subtreeRoot.left == null) {
            return subtreeRoot;
        }

        return min(subtreeRoot.left);
    }

    private TreeNode deleteMin(TreeNode subtreeRoot) {
        if (subtreeRoot.left == null) {
            size--;
            return null;
        }

        if (!isRed(subtreeRoot.left) && !isRed(subtreeRoot.left.left)) {
            subtreeRoot = moveRedLeft(subtreeRoot);
        }

        subtreeRoot.left = deleteMin(subtreeRoot.left);
        return balance(subtreeRoot);
    }

    private TreeNode moveRedLeft(TreeNode subtreeRoot) {

        flipColors(subtreeRoot);

        if (isRed(subtreeRoot.right.left)) {
            subtreeRoot.right = rotateRight(subtreeRoot.right);
            subtreeRoot = rotateLeft(subtreeRoot);
            flipColors(subtreeRoot);
        }

        return subtreeRoot;
    }

    private TreeNode moveRedRight(TreeNode subtreeRoot) {

        flipColors(subtreeRoot);

        if (isRed(subtreeRoot.left.left)) {
            subtreeRoot = rotateRight(subtreeRoot);
            flipColors(subtreeRoot);
        }

        return subtreeRoot;
    }

    private TreeNode balance(TreeNode subtreeRoot) {


        if (isRed(subtreeRoot.right)) {
            subtreeRoot = rotateLeft(subtreeRoot);
        }

        if (isRed(subtreeRoot.left) && isRed(subtreeRoot.left.left)) {
            subtreeRoot = rotateRight(subtreeRoot);
        }

        if (isRed(subtreeRoot.left) && isRed(subtreeRoot.right)) {
            flipColors(subtreeRoot);
        }

        return subtreeRoot;
    }

}
