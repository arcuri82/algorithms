package org.pg4200.compression;

import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 30-Oct-17.
 */
public class DnaCompressorTest {



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

        int nonCompressedSize = dna.getBytes(Charset.forName("utf-8")).length;

        byte[] compressed = DnaCompressor.compress(dna);

        assertTrue(compressed.length < nonCompressedSize);

        double ratio = (double) compressed.length / (double) nonCompressedSize;
        assertTrue(ratio < 0.33);

        assertTrue(ratio > 0.25);
    }

    @Test
    public void testIncreaseSize(){

        String dna = "A";
        checkPreserveInformation(dna);

        int nonCompressedSize = dna.getBytes(Charset.forName("utf-8")).length;

        byte[] compressed = DnaCompressor.compress(dna);

        assertTrue(compressed.length > nonCompressedSize);
    }
}