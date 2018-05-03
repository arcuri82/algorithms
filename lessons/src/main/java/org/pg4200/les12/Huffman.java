package org.pg4200.les12;

import java.util.*;

/**
 * Created by arcuri82 on 01-Nov-17.
 */
public class Huffman implements TextCompression  {

    @Override
    public  byte[] compress(String text) {

        Objects.requireNonNull(text);


        // First create a trie
        Node root = buildTrie(text);

        //then create a optimal encoding based on the trie
        Map<Character, String> codes = new HashMap<>();
        buildCodes(codes, root, "");

        BitWriter buffer = new BitWriter();

        /*
            then, write the trie, the length of the characters
            in the text, and each of these character based on the
            Huffman encoding from the trie
         */
        writeTrie(root, buffer);
        buffer.write(text.length());
        writeChars(text, codes, buffer);

        return buffer.extract();
    }

    @Override
    public  String decompress(byte[] data) {

        BitReader reader = new BitReader(data);
        StringBuilder buffer = new StringBuilder();

        Node root = readTrie(reader);

        int length = reader.readInt();

        for (int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {

                boolean bit = reader.readBoolean();

                if (bit) {
                    x = x.right;
                } else {
                    x = x.left;
                }
            }
            buffer.append(x.ch);
        }

        return buffer.toString();
    }


    //---------------------------------------------------------------------

    /**
     * Data structure needed to represent a Trie.
     * It is like a binary tree, but with each node having
     * up to 2 values ("ch" and "freq"), where one of them
     * ("ch") is used only in the leaves
     */
    protected  class Node implements Comparable<Node> {

        public final Character ch; // null if non-leaf node
        public final int freq; // how often char is in the text
        public final Node left;
        public final Node right;

        public Node(Character ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {

            return (left == null) && (right == null);
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }


    private  Node buildTrie(String text) {

        int[] freq = new int[65_536]; //2^16

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            //chars can be read as numbers from 0 to 2^16 - 1
            freq[c]++;
        }

        List<Node> list = new ArrayList<>(freq.length);

        /*
            Create 1 node per each char that appear at least
            once in the text.
         */
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                list.add(new Node((char) i, freq[i], null, null));
            }
        }

        /*
            Merge the 2 nodes with least frequency into a new subtree,
            having frequency being the sum of the two children.
            Stop only when a single node is left, which will be the
            root of a tree including all the nodes.
         */
        while (list.size() > 1) {
            Node left = deleteMinimum(list);
            Node right = deleteMinimum(list);
            Node parent = new Node(null, left.freq + right.freq, left, right);
            list.add(parent);
        }

        return list.get(0);
    }


    private  Node deleteMinimum(List<Node> list) {

        /*
            Note: this code could be optimized to handle a custom
            list for deletions of the minimum.
            But as anyway in a text only few distinct characters
            are used, it is not a big deal.
         */

        int min = 0;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(min)) < 0) {
                min = i;
            }
        }

        return list.remove(min);
    }

    protected  void writeTrie(Node node, BitWriter buffer) {

        /*
            The trick here is to use 1 bit to represent whether
            a node is a leaf (with following 16 bits for the char)
            or an internal one (with  2 following nodes)
         */

        if (node.isLeaf()) {
            buffer.write(true);
            buffer.write(node.ch);
            return;
        }

        buffer.write(false);
        writeTrie(node.left, buffer);
        writeTrie(node.right, buffer);
    }



    private  void buildCodes(Map<Character, String> codes, Node node, String s) {

        /*
            A code will be a 0/1 string
         */

        if (!node.isLeaf()) {
            buildCodes(codes, node.left, s + '0');
            buildCodes(codes, node.right, s + '1');
        } else {
            codes.put(node.ch, s);
        }
    }

    private  void writeChars(String text, Map<Character, String> codes, BitWriter buffer) {

        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            String code = codes.get(c);

            for (int j = 0; j < code.length(); j++) {
                /*
                    We look at each 0 and 1 in the string code,
                    and write it with a bit
                 */
                if (code.charAt(j) == '0') {
                    buffer.write(false);
                } else {
                    buffer.write(true);
                }
            }
        }
    }

    protected  Node readTrie(BitReader buffer) {

        /*
            When we read, we need to recreate the trie, but
            we do not care of the frequency values.
         */

        boolean isLeaf = buffer.readBoolean();
        if (isLeaf) {
            return new Node(buffer.readChar(), -1, null, null);
        } else {
            return new Node(null, -1, readTrie(buffer), readTrie(buffer));
        }
    }

    //---------------------------------------------------------------------


    @Override
    public  String getStatistics(String text) {

        Node root = buildTrie(text);
        Map<Character, String> codes = new HashMap<>();
        buildCodes(codes, root, "");

        List<Node> sortedList = new ArrayList<>();
        collectNodes(root, sortedList);
        Collections.sort(sortedList, Collections.reverseOrder());

        StringBuilder buffer = new StringBuilder();
        for (Node n : sortedList) {
            String c;
            if (n.ch == '\n') {
                c = "\\n";
            } else {
                c = String.valueOf(n.ch);
            }
            buffer.append("'" + c + "' (" + n.freq + "): " + codes.get(n.ch) + "\n");
        }

        return buffer.toString();
    }

    private  void collectNodes(Node node, List<Node> list) {
        if (node.isLeaf()) {
            list.add(node);
            return;
        }
        if (node.left != null) {
            collectNodes(node.left, list);
        }
        if (node.right != null) {
            collectNodes(node.right, list);
        }
    }

}
