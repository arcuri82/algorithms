package org.pg4200.ex11;

import org.pg4200.les11.BitReader;
import org.pg4200.les11.BitWriter;
import org.pg4200.les11.Huffman;

import java.nio.charset.StandardCharsets;

public class HuffmanIso extends Huffman {

    @Override
    protected void writeTrie(Node node, BitWriter buffer){
        if (node.isLeaf()) {
            buffer.write(true);
            byte b = node.ch.toString().getBytes(StandardCharsets.ISO_8859_1)[0];
            buffer.write(b);
            //buffer.write(node.ch);
            return;
        }

        buffer.write(false);
        writeTrie(node.left, buffer);
        writeTrie(node.right, buffer);
    }

    @Override
    protected Node readTrie(BitReader buffer){
        /*
            When we read, we need to recreate the trie, but
            we do not care of the frequency values.
         */

        boolean isLeaf = buffer.readBoolean();
        if (isLeaf) {
            char ch = new String(
                    new byte[] {buffer.readByte()},
                    StandardCharsets.ISO_8859_1)
                    .charAt(0);
            return new Node(ch, -1, null, null);
        } else {
            return new Node(null, -1, readTrie(buffer), readTrie(buffer));
        }
    }
}
