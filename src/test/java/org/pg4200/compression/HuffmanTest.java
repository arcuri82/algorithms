package org.pg4200.compression;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 01-Nov-17.
 */
public class HuffmanTest {


    private double checkCompressAndDecompress(String text){

        byte[] compressed = Huffman.compress(text);

        String res = Huffman.decompress(compressed);

        assertEquals(text, res);

        System.out.println(Huffman.getTrieStatistics(text));

        int originalLength = text.getBytes(Charset.forName("utf-16")).length;

        return (double) compressed.length / (double) originalLength;
    }

    @Test
    public void testSingleChar(){
        double ratio = checkCompressAndDecompress("a");
        assertTrue(ratio > 1);
    }

    @Test
    public void testSingleWord(){
        double ratio = checkCompressAndDecompress("foo");
        assertTrue(ratio > 1);
    }

    @Test
    public void testSentence(){
        double ratio = checkCompressAndDecompress(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ");

        assertTrue(ratio > 0.4);
        assertTrue(ratio < 0.5);
    }

    @Test
    public void testBook(){

        String text = new Scanner(HuffmanTest.class.getResourceAsStream("/compression/odyssey.mb.txt"), "UTF-8").useDelimiter("\\A").next();

        double ratio = checkCompressAndDecompress(text);
        assertTrue(ratio > 0.25);
        assertTrue(ratio < 0.3);
    }
}