package org.pg4200.les12;


import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Created by arcuri82 on 30-Oct-17.
 */
public class DnaCompressorTest {


    /**
     *   Having a compression "c" and a decompression "d",
     *   then, whatever the input "x" is, the following should
     *   always be true
     *
     *   x = d(c(x))
     *
     *   ie, if we compress and then decompress, we should get
     *   back the original input
     */
    private void checkPreserveInformation(String dna ){

        byte[] compressed = DnaCompressor.compress(dna);

        String decompressed = DnaCompressor.decompress(compressed);

        assertEquals(dna, decompressed);
    }

    @Test
    public void testPreserveInformation(){
        checkPreserveInformation("AAAGTACCTGAGT");
    }

    @Test
    public void testDecreaseSize(){

        String dna = "AAAGTACCTGAGTAAAGTACCTGAGTAAAGTACCTGAGTTTTGCTGCTGCTGCTGCTGCTGCTGCTGCTGCTTTTTTT";
        checkPreserveInformation(dna);

        int nonCompressedSize = dna.getBytes(StandardCharsets.UTF_8).length;

        byte[] compressed = DnaCompressor.compress(dna);

        assertTrue(compressed.length < nonCompressedSize);

        double ratio = (double) compressed.length / (double) nonCompressedSize;
        /*
            we get a good compression, which gets better for longer text,
            as the overhead of storing the trie has less impact
         */

        assertTrue(ratio < 0.33);

        //can't beat 1/4 ratio
        assertTrue(ratio >= 0.25);
    }

    @Test
    public void testIncreaseSize(){

        /*
            Compressing a single char make the data larger, as we still
            have to store at least 1 byte for the data plus 32 bits for the size "1"
         */

        String dna = "A";
        checkPreserveInformation(dna);

        int nonCompressedSize = dna.getBytes(StandardCharsets.UTF_8).length;

        byte[] compressed = DnaCompressor.compress(dna);

        assertTrue(compressed.length > nonCompressedSize);
    }
}