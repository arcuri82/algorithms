package org.pg4200.sol12;

import org.junit.jupiter.api.Test;
import org.pg4200.les12.Huffman;
import org.pg4200.les12.HuffmanTest;

import java.nio.charset.Charset;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by arcuri82 on 06-Nov-17.
 */
public class HuffmanIsoTest {

    private double checkCompressAndDecompress(Huffman huffman , String text, String charset){

        byte[] compressed = huffman.compress(text);

        String res = huffman.decompress(compressed);

        assertEquals(text, res);

        System.out.println(huffman.getStatistics(text));

        int originalLength = text.getBytes(Charset.forName(charset)).length;

        double ratio = (double) compressed.length / (double) originalLength;

        System.out.println("Original size for "+charset+": " + originalLength);
        System.out.println("Compressed size: " + compressed.length);
        System.out.println("Ratio for "+charset+": "  + ratio);

        return ratio;
    }


    @Test
    public void testCompareOnShortNorwegianSentence(){

        String text = "Jeg ønsker å få en god karakter i denne eksamenen";
        Huffman h16 = new Huffman();
        Huffman hiso = new HuffmanIso();

        double r16 = checkCompressAndDecompress(h16, text, "utf-8");
        double riso = checkCompressAndDecompress(hiso, text, "utf-8");

        assertTrue(riso < r16);
        assertTrue(r16 > 1);
        assertTrue(riso < 1);
    }


    @Test
    public void testCompareOnBook(){

        String text = new Scanner(HuffmanTest.class.getResourceAsStream("/compression/odyssey.mb.txt"), "UTF-8").useDelimiter("\\A").next();

        Huffman h16 = new Huffman();
        Huffman hiso = new HuffmanIso();

        double r16 = checkCompressAndDecompress(h16, text, "utf-8");
        double riso = checkCompressAndDecompress(hiso, text, "utf-8");

        assertTrue(riso < r16);

        double diff = r16 - riso;
        assertTrue(diff < 0.001);
    }
}