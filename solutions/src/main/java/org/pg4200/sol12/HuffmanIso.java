package org.pg4200.sol12;

import org.pg4200.les12.BitReader;
import org.pg4200.les12.BitWriter;
import org.pg4200.les12.Huffman;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by arcuri82 on 06-Nov-17.
 */
public class HuffmanIso extends Huffman {

    protected  void writeTrie(Node node, BitWriter buffer) {

        if (node.isLeaf()) {
            buffer.write(true);
            byte[] isoByte = ("" + node.ch).getBytes(StandardCharsets.ISO_8859_1);
            buffer.write(isoByte[0]);
            return;
        }

        buffer.write(false);
        writeTrie(node.left, buffer);
        writeTrie(node.right, buffer);
    }


    protected  Node readTrie(BitReader buffer) {
        boolean isLeaf = buffer.readBoolean();
        if (isLeaf) {
            byte isoByte = buffer.readByte();
            char c = new String(new byte[]{isoByte},  StandardCharsets.ISO_8859_1).charAt(0);
            return new Node(c, -1, null, null);
        } else {
            return new Node(null, -1, readTrie(buffer), readTrie(buffer));
        }
    }


}
