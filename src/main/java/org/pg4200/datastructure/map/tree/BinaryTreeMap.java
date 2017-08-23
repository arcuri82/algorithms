package org.pg4200.datastructure.map.tree;

/**
 * Created by arcuri82 on 22-Aug-17.
 */
public class BinaryTreeMap<K extends Comparable<K>, V> implements MyTreeBasedMap<K, V> {

    private class TreeNode {
        K key;
        V value;
        TreeNode left;
        TreeNode right;
    }

    private TreeNode root;

    private int size;

    @Override
    public void put(K key, V value) {
        root = put(key, value, root);
    }

    private TreeNode put(K key, V value, TreeNode subtree){

        if(subtree == null){
            TreeNode node = new TreeNode();
            node.key = key;
            node.value = value;
            size++;
            return node;
        }

        int cmp = key.compareTo(subtree.key);

        if(cmp < 0){
            subtree.left = put(key,value, subtree.left);
            return subtree;
        }

        if(cmp > 0 ){
            subtree.right = put(key,value, subtree.right);
            return subtree;
        }

        assert cmp == 0;
        subtree.value = value;

        return subtree;
    }


    @Override
    public void delete(K key) {

        TreeNode node = find(key, root);
        if (node == null) {
            //nothing to do
            return;
        }

        delete(key, node);
    }

    private TreeNode delete(K key, TreeNode subtreeRoot) {

        if (subtreeRoot == null) {
            return null;
        }

        int cmp = key.compareTo(subtreeRoot.key);

        if (cmp < 0) {
            subtreeRoot.left = delete(key, subtreeRoot.left);
            return subtreeRoot;
        }

        if (cmp > 0) {
            subtreeRoot.right = delete(key, subtreeRoot.right);
            return subtreeRoot;
        }

        assert cmp == 0;

        if (subtreeRoot.left == null) {
            return subtreeRoot.right;
        }

        if(subtreeRoot.right == null){
            return subtreeRoot.left;
        }

        TreeNode tmp = subtreeRoot;
        subtreeRoot = min(tmp.right);
        subtreeRoot.right =deleteMin(tmp.right);
        subtreeRoot.left = tmp.left;

        return subtreeRoot;
    }

    private TreeNode min(TreeNode subtreeRoot) {
        if (subtreeRoot.left == null) {
            return subtreeRoot;
        }
        return min(subtreeRoot.left);
    }

    private TreeNode deleteMin(TreeNode subtreeRoot) {

        if (subtreeRoot.left == null) {
            return subtreeRoot.right;
        }

        subtreeRoot.left = deleteMin(subtreeRoot.left);

        return subtreeRoot;
    }

    @Override
    public V get(K key) {

        TreeNode node = find(key, root);
        if (node != null) {
            return node.value;
        }

        return null;
    }

    private TreeNode find(K key, TreeNode subtreeRoot) {

        if(subtreeRoot == null){
            return null;
        }


        int cmp = key.compareTo(subtreeRoot.key);

        if(cmp == 0){
            return subtreeRoot;
        } else if (cmp > 0 && subtreeRoot.right != null) {
            //look at greater values in the right subtree
            return find(key, subtreeRoot.right);
        } else if (cmp < 0 && subtreeRoot.left != null) {
            //look at smaller values in the left subtree
            return find(key, subtreeRoot.left);
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

    private int depth(TreeNode node) {

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
