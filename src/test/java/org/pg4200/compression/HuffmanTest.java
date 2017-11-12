package org.pg4200.compression;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 01-Nov-17.
 */
public class HuffmanTest {


    /**
     *  Compress and decompress the text, checking that this works correctly,
     *  and also printing stats.
     *  Note: the string "text" is in UTF-16, but, when we consider its size
     *  in bytes, we can choose different encodings.
     */
    private double checkCompressAndDecompress(String text, String charset){

        Huffman huffman = new Huffman();

        byte[] compressed = huffman.compress(text);

        String res = huffman.decompress(compressed);

        assertEquals(text, res);

        System.out.println(huffman.getTrieStatistics(text));

        int originalLength = text.getBytes(Charset.forName(charset)).length;

        double ratio = (double) compressed.length / (double) originalLength;

        System.out.println("Original size for "+charset+": " + originalLength);
        System.out.println("Compressed size: " + compressed.length);
        System.out.println("Ratio for "+charset+": "  + ratio);

        return ratio;
    }

    @Test
    public void testSingleChar(){
        double ratio = checkCompressAndDecompress("a","utf-16");
        assertTrue(ratio > 1);
    }

    @Test
    public void testSingleWord(){
        double ratio = checkCompressAndDecompress("foo","utf-16");
        assertTrue(ratio > 1);
    }

    @Test
    public void testSentence(){
        double ratio = checkCompressAndDecompress(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                "utf-16");

        assertTrue(ratio > 0.4);
        assertTrue(ratio < 0.5);
    }

    @Test
    public void testBook(){

        /*
         * The actual book on disk is in UTF-8.
         * We must read it in UTF-8.
         * However, once loaded, the JVM does convert it to UTF-16
         * to be able to store it in a String object.
         *
         * Note: the following code does read all the lines from the given file.
         */
        String text = new Scanner(HuffmanTest.class.getResourceAsStream("/compression/odyssey.mb.txt"), "UTF-8").useDelimiter("\\A").next();

        /*
            Note: as the text in that file does not have special characters,
            its utf-16 representation is twice as long as the utf-8 one.
         */

        double ratio16 = checkCompressAndDecompress(text, "utf-16");

        assertTrue(ratio16 > 0.25);
        assertTrue(ratio16 < 0.3);

        double ratio8 = checkCompressAndDecompress(text, "utf-8");
        assertTrue(ratio8 < 0.6);
    }
}