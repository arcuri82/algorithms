package org.pg4200.les12.lzw;


import java.util.*;

/**
 * Created by arcuri82 on 03-May-18.
 */
public class MyTernarySearchTrie<T> {

    private Node root;

    private class Node {

        public char ch;
        public Node left;
        public Node middle;
        public Node right;
        public T value;
    }

    public T get(String key) {

        Node node = getNode(root, key, 0);
        if (node == null) {
            return null;
        }

        return node.value;
    }

    private Node getNode(Node node, String key, int depth) {

        if (node == null || key == null) {
            return null;
        }

        char ch = key.charAt(depth);

        if (ch < node.ch) {
            return getNode(node.left, key, depth);
        } else if (ch > node.ch) {
            return getNode(node.right, key, depth);
        } else if (depth < key.length() - 1) {
            return getNode(node.middle, key, depth + 1);
        } else {
            return node;
        }
    }

    public void put(String key, T value) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Invalid key");
        }

        root = putAndGet(root, key, value, 0);
    }

    private Node putAndGet(Node node, String key, T value, int depth) {

        char ch = key.charAt(depth);

        if (node == null) {
            node = new Node();
            node.ch = ch;
        }

        if (ch < node.ch) {
            node.left = putAndGet(node.left, key, value, depth);
        } else if (ch > node.ch) {
            node.right = putAndGet(node.right, key, value, depth);
        } else if (depth < key.length() - 1) {
            node.middle = putAndGet(node.middle, key, value, depth + 1);
        } else {
            node.value = value;
        }

        return node;
    }

    public String longestPrefixOf(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }

        int length = 0;
        Node node = root;
        int i = 0;

        while (node != null && i < input.length()) {
            char ch = input.charAt(i);

            if (ch < node.ch) {
                node = node.left;
            } else if (ch > node.ch) {
                node = node.right;
            } else {
                i++;
                if (node.value != null) {
                    length = i;
                }
                node = node.middle;
            }
        }

        return input.substring(0, length);
    }

    public Set<Pair<String, T>> entrySet(){

        Set<Pair<String, T>> set = new HashSet<>();
        collect(set, root, "");

        return set;
    }


    private void collect(Set<Pair<String, T>> set, Node node, String prefix){

        if(node.value != null){
            set.add(new Pair<>(prefix + node.ch, node.value));
        }

        if(node.left != null){
            collect(set, node.left, prefix);
        }
        if(node.right != null){
            collect(set, node.right, prefix);
        }
        if(node.middle != null){
            collect(set, node.middle, prefix + node.ch);
        }
    }
}
