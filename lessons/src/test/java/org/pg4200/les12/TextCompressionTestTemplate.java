package org.pg4200.les12;

import java.nio.charset.Charset;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by arcuri82 on 03-May-18.
 */
public abstract class TextCompressionTestTemplate {

    private String odysseyText;

    protected String getOdysseyText(){
        /*
         * The actual book on disk is in UTF-8.
         * We must read it in UTF-8.
         * However, once loaded, the JVM does convert it to UTF-16
         * to be able to store it in a String object.
         *
         * Note: the following code does read all the lines from the given file.
         */

        if(odysseyText == null){
            odysseyText = new Scanner(TextCompressionTestTemplate.class
                    .getResourceAsStream("/compression/odyssey.mb.txt"), "UTF-8")
                    .useDelimiter("\\A")
                    .next();
        }

        return odysseyText;
    }

    /**
     *  Compress and decompress the text, checking that this works correctly,
     *  and also printing stats.
     *  Note: the string "text" is in UTF-16, but, when we consider its size
     *  in bytes, we can choose different encodings.
     */
    protected double checkCompressAndDecompress(TextCompression cmp, String text, String charset){

        byte[] compressed = cmp.compress(text);

        String res = cmp.decompress(compressed);

        assertEquals(text, res);

        System.out.println(cmp.getStatistics(text));

        int originalLength = text.getBytes(Charset.forName(charset)).length;

        double ratio = (double) compressed.length / (double) originalLength;

        System.out.println("Original size for "+charset+": " + originalLength);
        System.out.println("Compressed size: " + compressed.length);
        System.out.println("Ratio for "+charset+": "  + ratio);

        return ratio;
    }
}