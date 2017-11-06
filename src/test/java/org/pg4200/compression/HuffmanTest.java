package org.pg4200.compression;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 01-Nov-17.
 */
public class HuffmanTest {


    private double checkCompressAndDecompress(String text, String charset){

        byte[] compressed = Huffman.compress(text);

        String res = Huffman.decompress(compressed);

        assertEquals(text, res);

        System.out.println(Huffman.getTrieStatistics(text));

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

        String text = new Scanner(HuffmanTest.class.getResourceAsStream("/compression/odyssey.mb.txt"), "UTF-8").useDelimiter("\\A").next();

        double ratio16 = checkCompressAndDecompress(text, "utf-16");

        assertTrue(ratio16 > 0.25);
        assertTrue(ratio16 < 0.3);

        double ratio8 = checkCompressAndDecompress(text, "utf-8");
        assertTrue(ratio16 < 0.6);
    }
}