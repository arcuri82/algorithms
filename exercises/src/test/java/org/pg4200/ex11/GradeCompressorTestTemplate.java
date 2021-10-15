package org.pg4200.ex11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class GradeCompressorTestTemplate {

    protected abstract GradeCompressor getNewInstance();

    @Test
    public void testOne(){

        GradeCompressor gc = getNewInstance();

        String data = "0A";
        byte[] compressed = gc.compress(data);
        assertTrue(compressed.length <= 2);

        String decompressed = gc.decompress(compressed);
        assertEquals(data, decompressed);
    }

    @Test
    public void testSeveral(){

        GradeCompressor gc = getNewInstance();

        String data = "0A1F2F3C12F13B14B27A201B497A";
        byte[] compressed = gc.compress(data);
        String decompressed = gc.decompress(compressed);
        assertEquals(data, decompressed);
    }

    @Test
    public void test100(){

        GradeCompressor gc = getNewInstance();

        String data = "";
        for(int i=0; i<100; i++){
            data += (i + "A");
        }

        byte[] compressed = gc.compress(data);
        assertTrue(compressed.length <= 150);

        String decompressed = gc.decompress(compressed);
        assertEquals(data, decompressed);
    }
}
