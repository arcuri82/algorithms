package org.pg4200.datastructure.map.tree;

/**
 * Created by arcuri82 on 24-Aug-17.
 */
public class RedBlackTreeMap<K extends Comparable<K>, V> implements MyTreeBasedMap<K, V> {

    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;
        boolean is_red; //false means 'black'
    }

    private TreeNode root;

    private int size;

    @Override
    public void put(K key, V value) {

        root = put(key, value, root);
        root.is_red = false;
    }

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

        //FIXME: check and explain if following are mutually exclusive if statements

        if (isRed(subtreeRoot.right) && !isRed(subtreeRoot.left)) {
            subtreeRoot = rotateLeft(subtreeRoot);
        }
        if (isRed(subtreeRoot.left) && isRed(subtreeRoot.left.left)) {
            subtreeRoot = rotateRight(subtreeRoot);
        }
        if (isRed(subtreeRoot.left) && isRed(subtreeRoot.right)) {
            setRedWithBlackChildren(subtreeRoot);
        }

        return subtreeRoot;
    }

    private boolean isRed(TreeNode node) {
        if (node == null) {
            return false;
        }
        return node.is_red;
    }

    private void setRedWithBlackChildren(TreeNode subtreeRoot) {

        subtreeRoot.is_red = true;
        if (subtreeRoot.left != null) {
            subtreeRoot.left.is_red = false;
        }
        if (subtreeRoot.right != null) {
            subtreeRoot.right.is_red = false;
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
    public void delete(K key) {
        // TODO
    }

    @Override
    public V get(K key) {

        return get(key, root);
    }

    private V get(K key, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            return null;
        }

        int cmp = key.compareTo(subtreeRoot.key);

        if (cmp == 0) {
            return subtreeRoot.value;
        } else if (cmp > 0 && subtreeRoot.right != null) {
            //look at greater values in the right subtree
            return get(key, subtreeRoot.right);
        } else if (cmp < 0 && subtreeRoot.left != null) {
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
}
