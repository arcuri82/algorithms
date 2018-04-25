package org.pg4200.exercise.ex12;

import org.pg4200.compression.BitReader;
import org.pg4200.compression.BitWriter;
import org.pg4200.compression.Huffman;

import java.nio.charset.Charset;

/**
 * Created by arcuri82 on 06-Nov-17.
 */
public class HuffmanIso extends Huffman {

    protected  void writeTrie(Node node, BitWriter buffer) {

        if (node.isLeaf()) {
            buffer.write(true);
            byte[] isoByte = ("" + node.ch).getBytes(Charset.forName("ISO-8859-1"));
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
            char c = new String(new byte[]{isoByte}, Charset.forName("ISO-8859-1")).charAt(0);
            return new Node(c, -1, null, null);
        } else {
            return new Node(null, -1, readTrie(buffer), readTrie(buffer));
        }
    }


}
