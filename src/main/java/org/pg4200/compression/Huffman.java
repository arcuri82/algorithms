package org.pg4200.compression;

import java.util.*;

/**
 * Created by arcuri82 on 01-Nov-17.
 */
public class Huffman {


    public  byte[] compress(String text) {

        Objects.requireNonNull(text);

        Node root = buildTrie(text);

        Map<Character, String> codes = new HashMap<>();
        buildCodes(codes, root, "");

        BitWriter buffer = new BitWriter();
        writeTrie(root, buffer);
        buffer.write(text.length());
        writeChars(text, codes, buffer);

        return buffer.extract();
    }

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

    public  String getTrieStatistics(String text) {

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

    protected  Node readTrie(BitReader buffer) {
        boolean isLeaf = buffer.readBoolean();
        if (isLeaf) {
            return new Node(buffer.readChar(), -1, null, null);
        } else {
            return new Node(null, -1, readTrie(buffer), readTrie(buffer));
        }
    }

    private  void writeChars(String text, Map<Character, String> codes, BitWriter buffer) {

        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            String code = codes.get(c);

            for (int j = 0; j < code.length(); j++) {
                if (code.charAt(j) == '0') {
                    buffer.write(false);
                } else {
                    buffer.write(true);
                }
            }
        }
    }

    protected  void writeTrie(Node node, BitWriter buffer) {

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
        if (!node.isLeaf()) {
            buildCodes(codes, node.left, s + '0');
            buildCodes(codes, node.right, s + '1');
        } else {
            codes.put(node.ch, s);
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

        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                list.add(new Node((char) i, freq[i], null, null));
            }
        }

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

    protected  class Node implements Comparable<Node> {

        public final Character ch;
        public final int freq;
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
}
