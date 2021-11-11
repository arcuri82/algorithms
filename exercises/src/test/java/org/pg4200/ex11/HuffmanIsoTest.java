package org.pg4200.ex11;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.pg4200.les11.Huffman;
import org.pg4200.les11.HuffmanTest;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class HuffmanIsoTest {

    @Test
    public void testCompareOnShortNorwegianSentence(){

        String testSubject = "Jeg ønsker å få en god karakter i denne eksamenen";
        compareOnText(testSubject);

    }

    @Test
    public void testRepetition(){
        String testSubject = "Jeg ønsker å få en god karakter i denne eksamenen \n"
                + "Jeg ønsker å få en god karakter i denne eksamenen \n"
                + "Jeg ønsker å få en god karakter i denne eksamenen \n"
                + "Jeg ønsker å få en god karakter i denne eksamenen \n"
                + "Jeg ønsker å få en god karakter i denne eksamenen \n";


        compareOnText(testSubject);
    }

    @Test
    public void compareOnBook(){
        String text = new Scanner(
                HuffmanTest.class.getResourceAsStream("/compression/odyssey.mb.txt"), "UTF-8")
                .useDelimiter("\\A")
                .next();

        compareOnText(text);
    }

    @Test
    public void testOratory(){
        String text = new Scanner(
                HuffmanTest.class.getResourceAsStream("/compression/Catiline.txt"), "UTF-8")
                .useDelimiter("\\A")
                .next();

        compareOnText(text);

    }

    private void compareOnText(String text){
        Huffman huff = new Huffman();
        HuffmanIso huffIso = new HuffmanIso();

        byte[] vt = text.getBytes(StandardCharsets.UTF_8);
        byte[] v1 = huff.compress(text);
        byte[] v2 = huffIso.compress(text);

        assertTrue(v2.length < v1.length);

        String textDecoded1 = huff.decompress(v1);
        String textDecoded2 = huffIso.decompress(v2);

        double compression1 = (double) v1.length/vt.length;
        double compression2 = (double) v2.length/vt.length;

        assertTrue(textDecoded1.length() == textDecoded2.length());
        assertTrue(textDecoded1.equalsIgnoreCase(text));
        assertTrue(textDecoded2.equalsIgnoreCase(text));




    }
}
