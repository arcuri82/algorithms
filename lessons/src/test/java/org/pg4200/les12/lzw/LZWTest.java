package org.pg4200.les12.lzw;

import org.junit.jupiter.api.Test;
import org.pg4200.les12.Huffman;
import org.pg4200.les12.TextCompressionTestTemplate;
import org.pg4200.les12.lzw.LZW;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 03-May-18.
 */
public class LZWTest extends TextCompressionTestTemplate {

    protected double checkCompressAndDecompress(String text, String charset){
        return checkCompressAndDecompress(new LZW(), text, charset);
    }


    @Test
    public void testSingleChar(){
        double ratio = checkCompressAndDecompress("a","utf-16");

        //here only 3 bytes (24 bits) were written, 12 bits for "a", and 12 bits for EOF
        assertTrue(ratio < 1);
    }

    @Test
    public void testSingleWord(){
        double ratio = checkCompressAndDecompress("foo","utf-16");
        assertTrue(ratio < 1);
    }

    @Test
    public void testSentence(){
        double ratio = checkCompressAndDecompress(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. ",
                "utf-16");

        //here seems worse than Huffman
        assertTrue(ratio > 0.5);
        assertTrue(ratio < 0.6);
    }

    @Test
    public void testBook(){

        String text = getOdysseyText();

        double ratio16 = checkCompressAndDecompress(text, "utf-16");

        assertTrue(ratio16 > 0.20);
        assertTrue(ratio16 < 0.25);

        double ratio8 = checkCompressAndDecompress(text, "utf-8");
        assertTrue(ratio8 < 0.5);

        double  huffman = checkCompressAndDecompress(new Huffman(), text, "utf-8");
        assertTrue(ratio8 < huffman);
    }

    @Test
    public void testInvalid(){

        String text = "私はアンドレアです";

        assertThrows(IllegalArgumentException.class, () -> checkCompressAndDecompress(text, "utf-8"));
    }
}