package org.pg4200.compression;

import org.junit.Test;

import java.nio.charset.Charset;

import static org.junit.Assert.*;

/**
 * Created by arcuri82 on 30-Oct-17.
 */
public class DnaCompressorTest {



    private void checkPreserveInformation(String dna ){

        DnaCompressor compressor = new DnaCompressor();

        byte[] compressed = compressor.compress(dna);

        String decompressed = compressor.decompress(compressed);

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

        DnaCompressor compressor = new DnaCompressor();
        byte[] compressed = compressor.compress(dna);

        assertTrue(compressed.length < nonCompressedSize);
    }

    @Test
    public void testIncreaseSize(){

        String dna = "A";
        checkPreserveInformation(dna);

        int nonCompressedSize = dna.getBytes(Charset.forName("utf-8")).length;

        DnaCompressor compressor = new DnaCompressor();
        byte[] compressed = compressor.compress(dna);

        assertTrue(compressed.length > nonCompressedSize);
    }
}